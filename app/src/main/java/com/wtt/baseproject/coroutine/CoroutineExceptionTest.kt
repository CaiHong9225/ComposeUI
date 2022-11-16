package com.wtt.mykotlin.coroutine

import kotlinx.coroutines.*


/**
 * Created by Wangzhan on 2022/1/13
 * supervisorScope 特点：任何一个任务出问题，并不会影响其他任务的工作
 * coroutineScope 特点：任何一个子协程异常退出，那么整体都将退出
 * @descr
 */
private fun getA(): String {
    println("正常a")
    return "正常a"
}

private fun getB(): String {
    println("正常b")
    return "正常b"
}

private fun getE(): String {
    throw NullPointerException()
}

/**
 * 会崩溃 coroutineScope会将异常抛到外层
 */
fun test3() {
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable -> println("接收到异常") }
    var launch = GlobalScope.launch(context = Dispatchers.IO) {
        coroutineScope {
            launch {
                println("launchA:" + Thread.currentThread())
            }
            launch(exceptionHandler) {
                println("launchB:" + Thread.currentThread())
                getE()
            }
            var launchC = launch {
                delay(1000)
                println("launchC:" + Thread.currentThread())
                getB()
            }
        }
    }
}

/**
 * 会崩溃 supervisorScope 将异常回传 内部无接收直接崩溃
 */
fun test2() {
    var launch = GlobalScope.launch(context = Dispatchers.IO) {
        supervisorScope {
            launch {
                println("launchA:" + Thread.currentThread())
            }
            launch() {
                println("launchB:" + Thread.currentThread())
                getE()
            }
            var launchC = launch {
                delay(1000)
                println("launchC:" + Thread.currentThread())
                getB()

            }
        }
    }
}

/**
 *supervisorScope 会将异常返回给子Job 异常会被CoroutineExceptionHandler捕获
 */
suspend fun test1() {
    delay(1000)
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable -> println("接收到异常") }
    var launch = GlobalScope.launch(context = Dispatchers.IO) {
        supervisorScope {
            launch {
                println("launchA:" + Thread.currentThread())
            }
            launch(exceptionHandler) {
                println("launchB:" + Thread.currentThread())
                getE()
            }
            //supervisorScope不会影响launchC的执行 只有B会崩溃
            var launchC = launch {
                delay(1000)
                println("launchC:" + Thread.currentThread())
                getB()
            }
        }
    }
}

/**
 *coroutineScope rethrow抛出的异常 AndroidExceptionPreHandler接收
 */
fun test() {
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable -> println("接收到异常") }
    var launch = GlobalScope.launch(context = Dispatchers.IO) {
        try {
            coroutineScope {
                launch {
                    println("launchA:" + Thread.currentThread())
                }
                launch(exceptionHandler) {
                    println("launchB:" + Thread.currentThread())
                    getE()
                }
                var launchC = launch {
                    delay(1000)
                    println("launchC:" + Thread.currentThread())
                    getB()
                }
            }
        } catch (e: Exception) {
            print("Err!")
        }

    }
}