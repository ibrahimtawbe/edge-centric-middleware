package admin

import admin_ipc.IPCMessage
import com.beust.klaxon.Klaxon
import io.javalin.Javalin
import io.javalin.websocket.WsContext
import io.mappedbus.MappedBusReader
import java.io.File
import java.nio.ByteBuffer
import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.thread
import java.io.InputStreamReader
import java.io.BufferedReader




private val sessionsMap = ConcurrentHashMap<String, WsContext>()

data class ClientMessage(val type: String, val query: String, val params: String ="")

fun main(args: Array<String>) {

    thread(start = true) {
        val reader: MappedBusReader = if (System.getProperty("os.name").toLowerCase().contains("win")) {
            MappedBusReader("c:\\tmp\\adminChannel", 200000L, 10000);
        } else {
            MappedBusReader("/tmp/adminChannel", 200000L, 10000);
        }
        reader.open()
        while (true) {
            var ipcMessage = IPCMessage()
            if (reader.next()) {
                var type = reader.readType();
                if (type == 0) {
                    reader.readMessage(ipcMessage)
                    val wsContext = sessionsMap[ipcMessage.sessionId]
                    if (wsContext != null && wsContext.session.isOpen) {
                        println("sending message to ${ipcMessage.sessionId}")
                        wsContext.send(ByteBuffer.wrap(ipcMessage.byteMessage))
                    }
                }
            }
        }
        reader.close()
    }

    Javalin.create {
    }.apply {
        ws("/admin") { ws ->
            ws.onConnect { ctx ->
                println("New Client Connection ${ctx.sessionId}");
                sessionsMap[ctx.sessionId] = ctx
            }
            ws.onClose { ctx ->
                println("Connection Closed ${ctx.sessionId}")
                val wsContext = sessionsMap.get(ctx.sessionId)
                if (wsContext != null && wsContext.session.isOpen) {
                    wsContext.session.close()
                }
            }
            ws.onMessage { ctx ->
                println("New Message ${ctx.sessionId}")
                parseMessage(ctx.message(), ctx.sessionId)
            }
        }
    }.start(7070)
}

fun parseMessage(message: String, sessionId: String) {
    val clientMessage = Klaxon().parse<ClientMessage>(message)
    println(clientMessage)
    when(clientMessage!!.type) {
        "report" -> {
            val command = "java -Xmx512m -jar edge.d_port.main.jar --sessionId=$sessionId reports${File.separator}${clientMessage.query}.xml ${clientMessage.params} "
            println(command)
            val process = Runtime.getRuntime().exec(command)
            var line: String
            val input = BufferedReader(InputStreamReader(process.errorStream))
            input.lines().forEach {
                println(it)
            }
            val input2 = BufferedReader(InputStreamReader(process.inputStream))
            input2.lines().forEach {
                println(it)
            }
            val e = process.waitFor()
            input.close()
            println("dreport ended $e")
        }
    }
}