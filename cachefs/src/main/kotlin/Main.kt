package cachefsmain

import io.objectbox.Box
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import org.apache.avro.generic.GenericRecord


//TODO to build from schema (avro schema is already there)
@Entity
data class Record(
    @Id var ID: Long = 0,
    var ORIGINAL_ID: String?,
    var CALLING_NUM: String?,
    var CALLED_NUM: String?,
    var START_TIME_I: Long?
)

var store = MyObjectBox.builder().name("objectbox-cachefs-db").build()

val box: Box<Record> = store.boxFor()

//TODO to be customized
fun insertInCache(record: GenericRecord) {
    box.put(
        Record(
            ORIGINAL_ID = record["ID"].toString(),
            CALLED_NUM = record["CALLED_NUM"].toString(),
            CALLING_NUM = record["CALLING_NUM"].toString(),
            START_TIME_I = record["START_TIME_I"] as Long
        )
    )
}