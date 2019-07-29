package cachefsmain;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0006\u0010!\u001a\u00020\"\u001a\u0006\u0010#\u001a\u00020\"\u001a\u000e\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&\u001a\u000e\u0010\'\u001a\u00020\"2\u0006\u0010(\u001a\u00020&\u001a\u000e\u0010)\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\"\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\"\u001c\u0010\u0016\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\"\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006*"}, d2 = {"box", "Lio/objectbox/Box;", "Lcachefsmain/Record;", "getBox", "()Lio/objectbox/Box;", "brokerTopicAlert", "", "getBrokerTopicAlert", "()Ljava/lang/String;", "brokerTopicBatch", "getBrokerTopicBatch", "confObj", "", "", "getConfObj", "()Ljava/util/Map;", "kafkaProducer", "Lorg/apache/kafka/clients/producer/KafkaProducer;", "getKafkaProducer", "()Lorg/apache/kafka/clients/producer/KafkaProducer;", "setKafkaProducer", "(Lorg/apache/kafka/clients/producer/KafkaProducer;)V", "store", "error/NonExistentClass", "getStore", "()Lerror/NonExistentClass;", "setStore", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "yaml", "Lorg/yaml/snakeyaml/Yaml;", "getYaml", "()Lorg/yaml/snakeyaml/Yaml;", "getBatch", "", "initKafkaClient", "insertInCache", "record", "Lorg/apache/avro/generic/GenericRecord;", "sendMessageToAlertTopic", "genericRecord", "sendMessageToBatchTopic", "cachefs"})
public final class MainKt {
    @org.jetbrains.annotations.NotNull()
    private static final org.yaml.snakeyaml.Yaml yaml = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, java.lang.Object> confObj = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String brokerTopicBatch = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String brokerTopicAlert = null;
    @org.jetbrains.annotations.NotNull()
    private static error.NonExistentClass store;
    @org.jetbrains.annotations.NotNull()
    private static final io.objectbox.Box<cachefsmain.Record> box = null;
    @org.jetbrains.annotations.NotNull()
    public static org.apache.kafka.clients.producer.KafkaProducer<java.lang.String, cachefsmain.Record> kafkaProducer;
    
    @org.jetbrains.annotations.NotNull()
    public static final org.yaml.snakeyaml.Yaml getYaml() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Map<java.lang.String, java.lang.Object> getConfObj() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getBrokerTopicBatch() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getBrokerTopicAlert() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final error.NonExistentClass getStore() {
        return null;
    }
    
    public static final void setStore(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final io.objectbox.Box<cachefsmain.Record> getBox() {
        return null;
    }
    
    public static final void insertInCache(@org.jetbrains.annotations.NotNull()
    org.apache.avro.generic.GenericRecord record) {
    }
    
    public static final void getBatch() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final org.apache.kafka.clients.producer.KafkaProducer<java.lang.String, cachefsmain.Record> getKafkaProducer() {
        return null;
    }
    
    public static final void setKafkaProducer(@org.jetbrains.annotations.NotNull()
    org.apache.kafka.clients.producer.KafkaProducer<java.lang.String, cachefsmain.Record> p0) {
    }
    
    public static final void initKafkaClient() {
    }
    
    public static final void sendMessageToBatchTopic(@org.jetbrains.annotations.NotNull()
    cachefsmain.Record record) {
    }
    
    public static final void sendMessageToAlertTopic(@org.jetbrains.annotations.NotNull()
    org.apache.avro.generic.GenericRecord genericRecord) {
    }
}