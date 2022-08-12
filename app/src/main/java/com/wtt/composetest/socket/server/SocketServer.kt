package com.wtt.composetest.socket.server

import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket


/**
 * Created by Wangzhan on 2022/8/12
 *
 * @descr
 */
object SocketServer {

    private val TAG = SocketServer::class.java.simpleName

    private const val SOCKET_PORT = 9527

    private var socket: Socket? = null

    private var serverSocket: ServerSocket? = null

    private lateinit var mCallback: ServerCallback

    private lateinit var outputStream: OutputStream

    var result = true

    /**
     * 连接服务
     */
    fun startServer(callback: ServerCallback): Boolean {
        mCallback = callback
        Thread {
            try {
                serverSocket = ServerSocket(SOCKET_PORT)
                while (result) {
                    socket = serverSocket?.accept()
                    //通知信息
                    mCallback.otherMsg("${socket?.inetAddress} to connected")
                    //这部分可以替换成线程池
                    ServerThread(socket!!, callback).start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                result = false
            }
        }.start()
        return result
    }

    /**
     * 关闭服务
     */
    fun stopServer() {
        socket?.apply {
            shutdownInput()
            shutdownOutput()
            close()
        }
        serverSocket?.close()
    }

    /**
     * 发送消息到Client
     */
    fun sendToClient(msg: String) {
        Thread {
            if (socket!!.isClosed) {
                Log.e(TAG, "sendToClient: Socket is closed")
                return@Thread
            }
            outputStream = socket!!.getOutputStream()
            try {
                outputStream.write(msg.toByteArray())
                outputStream.flush()
                mCallback.otherMsg("toClient: $msg")
                Log.e(TAG, "向客户端发送消息成功")
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e(TAG, "向客户端发送消息失败")
            }
        }.start()
    }

    class ServerThread(private var socket: Socket, private val callback: ServerCallback) :
        Thread() {
        override fun run() {
            val inputStream: InputStream?
            try {
                inputStream = socket.getInputStream()
                val buffer = ByteArray(1024)
                var len: Int
                var reveiverStr = ""
                if (inputStream.available() == 0) {
                    Log.e(TAG, "inputStream.available() = = 0")
                }
                while (inputStream.read(buffer).also { len = it } != -1) {
                    reveiverStr += String(buffer, 0, len, Charsets.UTF_8)
                    if (len < 1024) {
                        callback.receiveClientMsg(true, reveiverStr)
                        reveiverStr = ""
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.message.let {
                    Log.e("socket error", it.toString())
                }
                callback.receiveClientMsg(false, "")
            }
        }
    }
}