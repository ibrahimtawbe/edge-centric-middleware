package main;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.dom4j.Document;

public interface Parser {

    public abstract Document parse(Dataset<Row> dataset);
}
