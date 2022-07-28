package com.wtt.composetest.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.compose.ui.geometry.Offset
import androidx.core.view.ViewCompat


/**
 * Created by Wangzhan on 2022/5/7
 *
 * @descr
 */
class MoveView constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attr, defStyleAttr) {


    private var lastOffset = Offset(0f, 0f)


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                lastOffset = Offset(event.x, event.y)

            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - lastOffset.x
                val dy = event.y - lastOffset.y

                ViewCompat.offsetLeftAndRight(this, dx.toInt())
                ViewCompat.offsetTopAndBottom(this, dy.toInt())

            }
        }
        return super.onTouchEvent(event)
    }
}