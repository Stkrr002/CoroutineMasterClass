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

/**in this case cancellation is handled using isActive flag

fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        for (i in 1..1500) {
            if (!isActive) {
                break
            }
            print("$i ")
            Thread.sleep(1)
        }

    }
    delay(10)
    job.cancelAndJoin()
    println("\noops program ends here")
}
 */

/**
 * exception handling when cancellable suspend function is used

fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        try{
            for (i in 1..1500) {
                print("$i ")
                yield()
            }
        }catch (ex:CancellationException){
            println("\ncaught the exception ${ex.message}")
        }finally {
            println("finally block executed")
        }
    }
    job.cancelAndJoin()
    println("\noops program ends here")
}
 */

/**
 * case when code after yield is not executed in finally block

fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        try {
            for (i in 1..1500) {
                print("$i ")
                yield()
            }
        } catch (ex: CancellationException) {
            println("\ncaught the exception ${ex.message}")
        } finally {

                println("Finally block before suspending function")
                yield()
                println("finally block after suspending function")

        }
    }
    job.cancelAndJoin()
    println("\noops program ends here")
}

  */


/**
 * case when code after yield() is executed in finally block

fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        try {
            for (i in 1..1500) {
                print("$i ")
                yield()
            }
        } catch (ex: CancellationException) {
            println("\ncaught the exception ${ex.message}")
        } finally {
            withContext(NonCancellable) {
                println("Finally block before suspending function")
                yield()
                println("finally block after suspending function")
            }
        }
    }
    job.cancelAndJoin()
    println("\noops program ends here")
}
 */



/**
 * Creating custom exception message

fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        try {
            for (i in 1..1500) {
                print("$i ")
                yield()
            }
        } catch (ex: CancellationException) {
            println("\nCaught the exception: ${ex.message}")
        } finally {
                println("finally block")
        }
    }
    delay(1)
    job.cancel(CancellationException("Hurray! My custom exception"))
    job.join()
    println("oops program ends here")
}
 */



fun main(args: Array<String>) = runBlocking {
    println("Hey program starts here")
    val job: Job = launch(Dispatchers.Default) {
        try {
            for (i in 1..1500) {
                print("$i ")
                yield()
            }
        } catch (ex: CancellationException) {
            println("\nCaught the exception: ${ex.message}")
        } finally {
            println("finally block")
        }
    }
    delay(1)
    job.cancel(CancellationException("Hurray! My custom exception"))
    job.join()
    println("oops program ends here")
}