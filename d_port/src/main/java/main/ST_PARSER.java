package main;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class ST_PARSER implements Parser {
    @Override
    public Document parse(Dataset<Row> dataset) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("calls_started");
        List<Row> dataRows = dataset.collectAsList();
        System.out.println("dataset size" + dataRows.size());
        dataRows.forEach((row) -> {
            root.addElement("call")
                    .addAttribute("called_num", row.getString(0));
        });

        return document;
    }
}
