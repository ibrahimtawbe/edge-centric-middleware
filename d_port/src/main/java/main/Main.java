package main;

import io.mappedbus.MappedBusReader;
import io.mappedbus.MappedBusWriter;
import org.apache.spark.sql.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;
import pojo.CDR;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.Callable;

@CommandLine.Command(description = "Get Detailed Report from Data Source",
        name = "detailed_report", mixinStandardHelpOptions = true, version = "1.0")
public class Main implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The Report File")
    private File fileReport;

    @CommandLine.Option(names = {"-sid", "--sessionId"}, description = "ADMIN channel Session ID")
    private String sessionId = "";

    @CommandLine.Parameters(index = "1..*") String[] params;

    @Override
    public Integer call() throws Exception {

        ArrayList<String> inputs = new ArrayList<>(2);
        Yaml yaml = new Yaml();
        Map<String, Object> confObject = yaml.load(new FileInputStream(new File("conf/conf.yaml")));

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(fileReport);
            Element queryElement = document.getRootElement().element("query");
            String queryText = queryElement.attributeValue("text");
            System.out.println(queryText);
            for (Iterator<Element> it = queryElement.element("inputs").elementIterator(); it.hasNext(); ) {
                Element element = it.next();
                inputs.add(element.attributeValue("key"));
                // do something
            }
            String parserClassName = document.getRootElement()
                    .element("result").element("parser").attributeValue("className");

            if (params.length != inputs.size()) {
                return -2;
            }
            String paramValue;
            for(int i=0; i<inputs.size(); i++) {
                paramValue = params[i];
                queryText = queryText.replace(String.format(Locale.ENGLISH, "$%d", i), paramValue);
            }
            System.out.println(queryText);

            SparkSession spark = SparkSession
                    .builder()
                    .appName("CityPro Edge Connector")
                    .config("spark.master", "local")
                    .getOrCreate();


            Encoder<CDR> cdrEncoder = Encoders.bean(CDR.class);

            String jdbcUrl = confObject.get("providerDBUrl").toString();
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", confObject.get("providerDBUsername").toString());
            connectionProperties.put("password", confObject.get("providerDBPassword").toString());
            String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            connectionProperties.setProperty("Driver", driverClass);
            // register tables
            System.out.println("before reading from provider...");
            spark.read().jdbc(jdbcUrl, "CDR", connectionProperties).createOrReplaceTempView("CDR");
            Dataset<Row> dataSet = spark.sql(queryText);
            dataSet.show();
            Class<?> parserClass = Class.forName(parserClassName);
            Constructor<?> ctor = parserClass.getConstructor();
            Parser genericParser = (Parser) ctor.newInstance();
            System.out.println("before sending data...");
            sendData(genericParser.parse(dataSet), sessionId);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void sendData(Document xmlDocument, String sessionId) {
        try {

            MappedBusWriter writer;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                writer = new MappedBusWriter("c:\\tmp\\adminChannel", 200000L, 10000);
            } else {
                writer = new MappedBusWriter("/tmp/adminChannel", 200000L, 10000);
            }
            writer.open();
            IPCMessage ipcMessage = new IPCMessage();
            ipcMessage.setSessionId(sessionId);
            ipcMessage.setByteMessage(xmlDocument.asXML().getBytes());
            writer.write(ipcMessage);
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        //sendData();

/*

*/

        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }


}

