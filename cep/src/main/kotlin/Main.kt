package cepmain
import io.siddhi.core.SiddhiManager
import io.siddhi.core.event.Event
import io.siddhi.core.stream.input.InputHandler
import io.siddhi.core.stream.output.StreamCallback
import io.siddhi.core.util.EventPrinter
import java.util.*


// Create Siddhi Manager
var siddhiManager = SiddhiManager()

//Siddhi Application
//TODO test sample build if from conf files
var siddhiApp = "" +
        "define stream EventStream (ID string, CALLING_NUM string, CALLED_NUM string, START_TIME_I long); " +
        "" +
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
            //EventPrinter.print(events)
            //To convert and print event as a map
            //EventPrinter.print(toMap(events));
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