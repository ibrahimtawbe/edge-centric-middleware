import cachefsmain.getBatch
import cachefsmain.initKafkaClient
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.*
import kotlin.concurrent.timer


fun main(args: Array<String>) {
    val yaml = Yaml()
    val confObj: Map<String, Any> = yaml.load(File("conf/conf.yaml").inputStream())

    val batchInterval:Int = confObj["batchInterval"] as Int
    initKafkaClient()
    //TODO add option to support specific date - also applicable to timer
    timer("batcher", false, 30000,batchInterval.toLong()* 60000) {
        getBatch()
    }

}
