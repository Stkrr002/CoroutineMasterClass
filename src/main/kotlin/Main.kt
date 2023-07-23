import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main(args: Array<String>)= runBlocking {
    println("Hey program starts here: ${Thread.currentThread().name}")
    GlobalScope.launch {
        delay(1000)
        println("Sumit is working in background thread: ${Thread.currentThread().name}")
    }
    Thread.sleep(2000)
    println("hey program ends here: ${Thread.currentThread().name}")
}








