package main;

import org.apache.spark.sql.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import picocli.CommandLine;
import pojo.CDR;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Callable;

@CommandLine.Command(description = "Get Detailed Report from Data Source",
        name = "detailed_report", mixinStandardHelpOptions = true, version = "1.0")
public class Main implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The Report File")
    private File fileReport;

    @CommandLine.Parameters(index = "1..*") String[] params;

    @Override
    public Integer call() throws Exception {

        ArrayList<String> inputs = new ArrayList<>(2);

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

            String jdbcUrl = "jdbc:sqlserver://localhost:1433;database=telecom";
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", "citypro");
            connectionProperties.put("password", "citypro2019");
            String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            connectionProperties.setProperty("Driver", driverClass);
            // register tables
            spark.read().jdbc(jdbcUrl, "CDR", connectionProperties).createOrReplaceTempView("CDR");
            Dataset<Row> dataSet = spark.sql(queryText);
            Class<?> parserClass = Class.forName(parserClassName);
            Constructor<?> ctor = parserClass.getConstructor();
            Parser genericParser = (Parser) ctor.newInstance();
            sendData(genericParser.parse(dataSet));

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void sendData(Document xmlDocument) {
        try {
            // Connect to the pipe
            RandomAccessFile pipe = new RandomAccessFile("\\\\.\\pipe\\sqlresp", "rw");
            String echoText = "Hello word\n";
            // write to pipe
            pipe.write(xmlDocument.asXML().getBytes());
            // read response
            pipe.close();
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

