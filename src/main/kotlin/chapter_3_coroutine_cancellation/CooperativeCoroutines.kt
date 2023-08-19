package chapter_3_coroutine_cancellation

import kotlinx.coroutines.*

/**in this case coroutine will not get canceled as Thread.sleep() is used here
 *
fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch {
        for (i in 1..500) {
            print("$i ")
            Thread.sleep(50)
        }

    }
    delay(10)
    job.cancelAndJoin()
    println("\noops program ends here")
}

*/




/**in this case coroutine will get canceled
 *
fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch {
        for (i in 1..500) {
            print("$i ")
           delay(50)
        }

    }
    delay(10)
    job.cancelAndJoin()
    println("\noops program ends here")
}

 */