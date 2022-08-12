package com.wtt.composetest.socket.client

import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket


/**
 * Created by Wangzhan on 2022/8/12
 *
 * @descr
 */
object SocketClient {
    private val TAG = SocketClient::class.java.simpleName

    private const val SOCKET_PORT = 9527

    private var socket: Socket? = null

    private var outputSteam: OutputStream? = null

    private var inputStreamReader: InputStreamReader? = null

    private lateinit var mCallback: ClientCallback

    fun connectServer(ipAddress: String, callback: ClientCallback): Boolean {
        var result = false
        mCallback = callback
        Thread {
            try {
                socket = Socket(ipAddress, SOCKET_PORT)
                result = true
            } catch (e: IOException) {
                e.printStackTrace()
                result = false
            }
        }.start()
        return result
    }

    fun closeConnect() {
        inputStreamReader?.close()
        outputSteam?.close()
        socket?.apply {
            shutdownInput()
            shutdownOutput()
            close()
        }
        Log.e(TAG, "关闭连接")
    }

    /**
     * 发送数据至服务器
     * @param msg 要发送的字符串
     */
    fun sendToServer(msg: String) {
        Thread {
            if (socket!!.isClosed) {
                Log.e(TAG, "sendToServer: Socket is closed")
                return@Thread
            }
            outputSteam = socket!!.getOutputStream()
            try {
                outputSteam?.let {
                    it.write(msg.toByteArray())
                    it.flush()
                    mCallback.otherMsg("toServer:$msg")
                    "success"
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e(TAG, "向服务端发送消息失败")
            }

        }.start()
    }

    class ClientThread(private val socket: Socket, private val callback: ClientCallback) :
        Thread() {
        override fun run() {
            val inputStream: InputStream?
            try {
                inputStream = socket.getInputStream()
                var buffer = ByteArray(1024)
                var len: Int
                var receiverStr = ""
                if (inputStream.available() == 0) {
                    Log.e(TAG, "inputStream.available() == 0")
                }
                while (inputStream.read(buffer).also { len = it } != -1) {
                    receiverStr += String(buffer, 0, len, Charsets.UTF_8)
                    if (len < 1024) {
                        callback.reveiveServerMsg(receiverStr)
                        receiverStr = ""
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                e.message?.let {
                    Log.e("socket error", it)
                }
                callback.reveiveServerMsg("")
            }
        }
    }
}