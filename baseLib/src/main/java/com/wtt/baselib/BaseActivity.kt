package com.wtt.baselib

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.wtt.baselib.utils.LogUtils
import com.wtt.baselib.utils.StatusBarUtils


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "BaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        if (layoutID() > 0) {
            setContentView(layoutID())
        }
        initView()
        initData()
    }

    protected abstract fun layoutID(): Int
    protected abstract fun initView()
    protected abstract fun initData()


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        LogUtils.d(TAG, "onKeyDown keyCode:$keyCode")
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 设置顶部状态栏的颜色（默认为白色背景-黑色文字）
     */
    protected fun setStatusBarColor(): Int {
        //如果状态栏文字能变黑那么背景设置为白色，否则返回背景灰色文本默认为白色
        return if (StatusBarUtils.setStatusBarBlackText(this)) {
            Color.WHITE
        } else {
            Color.parseColor("#B0B0B0")
        }
    }

    /**
     * 动态的设置状态栏颜色
     * 当颜色为白色的时候显示白底黑字
     * 其他颜色为其他颜色底白色字
     * 一般由子类重写
     */
    fun setStatusBarColor(color: Int) {
        if (color == Color.WHITE) {
            //变黑色文字成功
            if (StatusBarUtils.setStatusBarBlackText(this)) {
                StatusBarUtils.setColor(this, Color.WHITE)
            } else {
                StatusBarUtils.setColor(this, Color.parseColor("#B0B0B0"))
            }

        } else {

            //变为白色文字成功
            StatusBarUtils.setStatusBarWhiteText(this)
            StatusBarUtils.setColor(this, color)

        }
    }

    fun setStatusBarBlackText(){
        StatusBarUtils.setStatusBarBlackText(this)
    }

    fun setStatusBarWhiteText(){
        StatusBarUtils.setStatusBarWhiteText(this)
    }
}