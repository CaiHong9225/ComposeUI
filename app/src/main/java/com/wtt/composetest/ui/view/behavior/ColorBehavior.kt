package com.wtt.composetest.ui.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.wtt.composetest.R
import com.wtt.composetest.ui.view.MoveView
import kotlin.random.Random


/**
 * Created by Wangzhan on 2022/5/7
 *
 * @descr
 */
class ColorBehavior(val context: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<MoveView>(context, attrs) {


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
        child: MoveView,
        dependency: View
    ): Boolean {
        return dependency is MoveView
    }

    // 改变当前的状
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: MoveView,
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
