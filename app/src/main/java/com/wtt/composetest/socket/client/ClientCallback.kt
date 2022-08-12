package com.wtt.composetest.socket.client


/**
 * Created by Wangzhan on 2022/8/12
 *
 * @descr
 */
interface ClientCallback {

    /**
     * 接受 服务端的信息
     * @param msg
     */
    fun reveiveServerMsg(msg: String)

    fun otherMsg(msg: String)
}