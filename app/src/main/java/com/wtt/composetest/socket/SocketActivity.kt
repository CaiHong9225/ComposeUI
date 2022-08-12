package com.wtt.composetest.socket

import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wtt.composetest.R
import com.wtt.composetest.databinding.ActivitySocketBinding
import com.wtt.composetest.socket.client.ClientCallback
import com.wtt.composetest.socket.client.SocketClient
import com.wtt.composetest.socket.server.ServerCallback
import com.wtt.composetest.socket.server.SocketServer

class SocketActivity : AppCompatActivity(), ServerCallback, ClientCallback {
    private val TAG = SocketActivity::class.java.simpleName

    private lateinit var binding: ActivitySocketBinding

    private val buffer = StringBuffer()

    //是否为服务端
    private var isServer = true

    //Socket服务是否已经打开
    private var openSocket = false

    //Socket服务是否已经连接
    private var connectSocket = false


    private val messages = ArrayList<Message>()
    private lateinit var msgAdapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.tvIpAddress.text = "Ip Address: ${getIp()}"
        //服务端和客户端切换
        binding.rg.setOnCheckedChangeListener { _, checkedId ->
            isServer = when (checkedId) {
                R.id.rb_server -> true
                R.id.rb_client -> false
                else -> true
            }
            binding.layServer.visibility = if (isServer) View.VISIBLE else View.GONE
            binding.layClient.visibility = if (isServer) View.GONE else View.VISIBLE

            binding.etMsg.hint = if (isServer) "发送给客户端" else "发送给服务端"
        }
        //开启服务/关闭服务 服务端处理
        binding.btnStartService.setOnClickListener {
            openSocket = if (openSocket) {
                SocketServer.stopServer();false
            } else SocketServer.startServer(this)
            //显示日志
            updateList(if (isServer) 1 else 2, if (openSocket) "开启服务" else "关闭服务")
            //改变按钮文字
            binding.btnStartService.text = if (openSocket) "关闭服务" else "开启服务"
        }
        //连接服务/断开连接 客户端处理
        binding.btnConnectService.setOnClickListener {
            val ip = binding.etIpAddress.text.toString()
            if (ip.isEmpty()) {
                showMsg("请输入IP地址")
                return@setOnClickListener
            }
            connectSocket = if (connectSocket) {
                SocketClient.closeConnect();false
            } else {
                SocketClient.connectServer(ip, this);true
            }
            updateList(if (isServer) 1 else 2, if (connectSocket) "连接服务" else "关闭连接")
            binding.btnConnectService.text = if (connectSocket) "关闭连接" else "连接服务"
        }
        //发送消息 给 服务端/客户端
        binding.btnSendMsg.setOnClickListener {
            var msg = binding.etMsg.text.toString()
            if (msg.isEmpty()) {
                showMsg("请输入要发送的信息")
                return@setOnClickListener
            }
            //检查是否能否发送信息
            //检查是否能发送消息
            val isSend = if (openSocket) openSocket else if (connectSocket) connectSocket else false
            if (!isSend) {
                showMsg("当前未开启服务或连接服务");return@setOnClickListener
            }
            if (isServer) {
                SocketServer.sendToClient(msg)
            } else {
                SocketClient.sendToServer(msg)
            }
            updateList(if (isServer) 1 else 2, msg)
            binding.etMsg.setText("")
        }
        msgAdapter = MsgAdapter(messages)
        binding.rvMsg.apply {
            layoutManager = LinearLayoutManager(this@SocketActivity)
            adapter = msgAdapter
        }
    }

    private fun updateList(type: Int, msg: String) {
        messages.add(Message(type, msg))
        runOnUiThread {
            (if (messages.size == 0) 0 else messages.size - 1).apply {
                msgAdapter.notifyItemChanged(this)
                binding.rvMsg.smoothScrollToPosition(this)
            }
        }
    }

    private fun getIp() =
        intToIp((applicationContext.getSystemService(WIFI_SERVICE) as WifiManager).connectionInfo.ipAddress)

    private fun intToIp(ip: Int) =
        "${(ip and 0xFF)}.${(ip shr 8 and 0xFF)}.${(ip shr 16 and 0xFF)}.${(ip shr 24 and 0xFF)}"


    private fun showMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun reveiveServerMsg(msg: String) {
        updateList(1, msg)
    }

    override fun receiveClientMsg(success: Boolean, msg: String) {
        updateList(2, msg)
    }

    override fun otherMsg(msg: String) {
        Log.d(TAG, "otherMsg: $msg")
    }
}