package cepmain
import io.siddhi.core.SiddhiManager
import io.siddhi.core.event.Event
import io.siddhi.core.stream.input.InputHandler
import io.siddhi.core.stream.output.StreamCallback
import io.siddhi.core.util.EventPrinter
import org.apache.kafka.clients.producer.KafkaProducer
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.*


val yaml = Yaml()
val confObj: Map<String, Any> = yaml.load(File("conf/conf.yaml").inputStream())

// Create Siddhi Manager
var siddhiManager = SiddhiManager()

//Siddhi Application
//TODO schema must be from conf
var siddhiApp = "" +
        "define stream EventStream (ID string, CALLING_NUM string, CALLED_NUM string, START_TIME_I long); " +
        "@sink(type='kafka',topic='${confObj["brokerTopicAlert"]}',partition.no='0',bootstrap.servers='${confObj["brokerServer"]}:${confObj["brokerPort"]}',@map(type='json'))" +
        "define stream OutputStream (ID string, CALLING_NUM string, CALLED_NUM string, START_TIME_I long); " +
        "@info(name = 'query1') " +
        "from EventStream[str:contains(CALLING_NUM, '07')] " +
        "select ID, CALLING_NUM, CALLED_NUM, START_TIME_I " +
        "insert into OutputStream;"

//Generate runtime
var siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp)

fun pushToCEPStream(event: Array<Any>) {
    siddhiAppRuntime.getInputHandler("EventStream").send(event)

}

fun startCEP() {
    println("Hello CEP!")
    siddhiAppRuntime.addCallback("OutputStream", object : StreamCallback() {
        override fun receive(events: Array<Event>) {
            println(Arrays.toString(events))
            events.forEach {

               /* var reord  = cachefsmain.Record(it.getData(0) as String,
                    it.getData(1).toString()
                )
                sendMessageToAlertTopic()*/
            }

            //EventPrinter.print(events)
            //To convert and print event as a map
            EventPrinter.print(toMap(events));
        }
    })

    //Start processing
    siddhiAppRuntime.start();
}

fun stopCEP() {
    //Shutdown runtime
    siddhiAppRuntime.shutdown();
    //Shutdown Siddhi Manager
    siddhiManager.shutdown();
}