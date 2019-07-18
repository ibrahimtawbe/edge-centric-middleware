package cachefsmain

import com.fasterxml.jackson.databind.ObjectMapper
import io.objectbox.Box
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.kotlin.boxFor
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.Serializer
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.*
import kotlin.system.exitProcess


//TODO to build from schema (avro schema is already there)
@Entity
data class Record(
    @Id var ID: Long = 0,
    var ORIGINAL_ID: String?,
    var CALLING_NUM: String?,
    var CALLED_NUM: String?,
    var START_TIME_I: Long?,
    var INSERT_TIME: Long?
)

class RecordSerializer : Serializer<Record> {
    override fun serialize(topic: String?, data: Record?): ByteArray {
        return try {
            val objectMapper = ObjectMapper()
            objectMapper.writeValueAsString(data).toByteArray(Charsets.UTF_8)
        } catch (e: Exception) {
            ByteArray(0);
        }
    }

}

val yaml = Yaml()
val confObj: Map<String, Any> = yaml.load(File("conf/conf.yaml").inputStream())

val brokerTopicBatch: String = confObj["brokerTopicBatch"] as String

var store = MyObjectBox.builder().name("objectbox-cachefs-db").build()
val box: Box<Record> = store.boxFor()



//TODO to be customized
fun insertInCache(record: GenericRecord) {
    box.put(
        Record(
            ORIGINAL_ID = record["ID"].toString(),
            CALLED_NUM = record["CALLED_NUM"].toString(),
            CALLING_NUM = record["CALLING_NUM"].toString(),
            START_TIME_I = record["START_TIME_I"] as Long,
            INSERT_TIME = System.currentTimeMillis()
        )
    )
}

fun getBatch() {
    // also customized because of Record Schema
    box.query().build().forEach {
        sendMessageToBatchTopic(it)
    }
    println("cachefs/getBatch Ended")
}

lateinit var kafkaProducer: KafkaProducer<String, Record>
fun initKafkaClient() {
    val props = Properties()
    props.put("bootstrap.servers", "${confObj["brokerServer"]}:${confObj["brokerPort"]}")
    props.put("acks", "all")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", RecordSerializer().javaClass.name)

    kafkaProducer = KafkaProducer(props)
}

fun sendMessageToBatchTopic(record: Record) {
    kafkaProducer.send(
        ProducerRecord<String, Record>(
            brokerTopicBatch,
            "${record.ID}_${record.ORIGINAL_ID}", record
        )
    ) { _, e ->
        if (e != null) {
            e.printStackTrace()
        } else {
            //TODO use batch remove to optimize performance
            println("Kafka Send TODELETE ${record.ID}")
            box.query().equal(Record_.ID, record.ID).build().remove()
        }
    }
}