package com.wtt.compose.ui.view.myview.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.wtt.compose.R


/**
 * Created by Wangzhan on 2022/5/7
 *
 * @descr
 */
class ColorBehavior(val context: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<com.wtt.compose.ui.view.myview.MoveView>(context, attrs) {


    companion object {

        const val TAG = "ColorBehavior"
    }

    /*
     * TODO 判断跟随变化的 View
     * @param parent: CoordinatorLayout
     * @param child: 当前的 view
     * @param dependency: 需要依赖的 View
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: com.wtt.compose.ui.view.myview.MoveView,
        dependency: View
    ): Boolean {
        return dependency is com.wtt.compose.ui.view.myview.MoveView
    }

    // 改变当前的状
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: com.wtt.compose.ui.view.myview.MoveView,
        dependency: View
    ): Boolean {
        // 随机颜色
        child.setBackgroundColor(context.randomColor())
        return super.onDependentViewChanged(parent, child, dependency)
    }
}

private fun Context.randomColor(): Int {
    return R.color.black
}
