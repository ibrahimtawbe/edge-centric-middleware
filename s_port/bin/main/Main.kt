package main

import cachefsmain.insertInCache
import cepmain.pushToCEPStream
import cepmain.startCEP
import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.util.InternalAPI
import io.ktor.util.moveToByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.avro.Schema
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.net.InetSocketAddress
import org.apache.avro.generic.GenericRecord
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.io.DatumReader
import org.apache.avro.io.DecoderFactory
import org.apache.avro.util.Utf8
import java.nio.ByteBuffer


@UseExperimental(InternalAPI::class)
fun main(args: Array<String>) {

    val yaml = Yaml()
    val confObj:Map<String, Any> = yaml.load(File("conf/conf.yaml").inputStream())

    val schemaParser : Schema.Parser = Schema.Parser()
    val schema = schemaParser.parse(File("conf/cdr.avsc").inputStream())
    val datumReader = GenericDatumReader<GenericRecord>(schema)

    runBlocking {
       startCEP()
   }

    //pushToCEPStream(arrayOf("IBM2", "07437777734", "070707", 1))

    runBlocking {
        val server = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp()
            .bind(InetSocketAddress(confObj["host"].toString(), confObj["sport"] as Int))
        println("Started echo server at ${server.localAddress}")
        while (true) {
            val socket = server.accept()
            launch {
                println("Socket accepted: ${socket.remoteAddress}")

                val input = socket.openReadChannel()
                val output = socket.openWriteChannel(autoFlush = true)

                try {
                    while (true) {

                        val message = input.read {
                            try {
                                val byteArray = it.moveToByteArray()
                                println("received array with size " + byteArray.size)
                                val decoder = DecoderFactory.get().binaryDecoder(byteArray, null)
                                val genericRecord = datumReader.read(null, decoder)
                                val listAny = mutableListOf<Any>()
                                genericRecord.schema.fields.forEach { it1 ->
                                    var fieldValue =   genericRecord.get(it1.name())
                                    if (fieldValue is Utf8) {
                                        fieldValue = fieldValue.toString()
                                    }
                                    listAny.add(fieldValue)
                                }
                                insertInCache(genericRecord)
                                pushToCEPStream(listAny.toTypedArray())
                                println(genericRecord)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    socket.close()
                }
            }
        }
    }



}