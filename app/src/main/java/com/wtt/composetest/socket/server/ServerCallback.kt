package com.wtt.composetest.socket.server


/**
 * Created by Wangzhan on 2022/8/12
 *
 * @descr
 */
interface ServerCallback {

    //客户端消息
    fun receiveClientMsg(success: Boolean, msg: String)

    //其他消息
    fun otherMsg(msg: String)
}