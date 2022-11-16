package com.wtt.mykotlin.coroutine

import android.util.Log
import kotlinx.coroutines.*


/**
 * Created by Wangzhan on 2022/1/13
 *
 * @descr 启动协程示例
 */
class LaunchCoroutine {

    /**
     * 具有阻塞效果的协程启动 不管在子线程还是主线程 都会使Main处于Wait状态
     *
     * runBlocking是启动新协程的一种方法。
     * runBlocking启动一个新的协程，并阻塞它的调用线程，直到里面的代码执行完毕。
     */
    fun runBlockingGo() {
        println("aaaaaaaaa ${Thread.currentThread().name}")

        runBlocking {
            for (i in 0..10) {
                println("$i ${Thread.currentThread().name}")
                delay(100)
            }
            launch {

            }
        }

        println("bbbbbbbbb ${Thread.currentThread().name}")

        runBlocking(Dispatchers.IO) {
            for (i in 0..10) {
                println("$i ${Thread.currentThread().name}")
                delay(100)
            }

        }
/*        aaaaaaaaa main
                0 DefaultDispatcher-worker-1
        1 DefaultDispatcher-worker-1
        2 DefaultDispatcher-worker-1
        3 DefaultDispatcher-worker-4
        4 DefaultDispatcher-worker-4
        5 DefaultDispatcher-worker-6
        6 DefaultDispatcher-worker-7
        7 DefaultDispatcher-worker-7
        8 DefaultDispatcher-worker-9
        9 DefaultDispatcher-worker-1
        10 DefaultDispatcher-worker-5
        bbbbbbbbb main*/
    }

    /**
     * launch可以启动一个协程，但不会阻塞调用线程，但是launch必须要在协程作用域中才能调用。
     *
     * 不会阻塞线程
     */
    fun launchGo() {
        GlobalScope.launch(Dispatchers.IO) {
            print("进入协程async")
            delay(1000)
            print("协程执行完成async")
        }

    }

    //协程执行进程切换调度
    //协程在主线程启动
    /**
     *
     *
     * 协程会在Main主线程创建，但是中间有一部分逻辑需要在子线程中执行，使用withContext实现。
     * withContext中的逻辑执行完成后会再次切换回主线程。注   withContext会阻塞协程
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun withContextGo() {
        GlobalScope.launch(Dispatchers.Main) {
            log("thread1：${Thread.currentThread().name}")
            //切换到子线程操作  会block
            withContext(Dispatchers.IO) {
                log("thread2：${Thread.currentThread().name}")
            }
            log("thread3：${Thread.currentThread().name}")
        }
    }
    //launch方法中执行多个async
    /**
     * 注：await() 方法只能在一个协程内部调用，在主线程调用会报错 代码中必须两个aysnc函数都返回了结果才会调用打印日志方法
     */
    fun launchTest() {
        GlobalScope.launch {
            val result1 = async {
                delay(1000)
                "result1"
            }
            val result2 = async {
                delay(2000)
                "result2"
            }
            print("两个async:${result1.await()}:::${result2.await()}")
        }
    }


    /**
     * 取消协程
     *
     * 会打印 1  3 4  JobCancellationException job会收到delay影响不执行2
     */
    fun coroutineCancel() = runBlocking {
        val job1 = launch { // ①
            log(1)
            delay(1000) // ②
            log(2)
        }
        delay(100)
        log(3)
        job1.cancel() // ③
        log(4)
    }

    fun log(msg: Any) {
        Log.d("LaunchCoroutine", msg.toString())
    }
}