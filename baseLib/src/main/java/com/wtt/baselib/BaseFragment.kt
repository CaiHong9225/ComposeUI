package com.wtt.baselib

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseFragment<T> : Fragment(), View.OnClickListener {
    protected var mContext: Context? = null
    protected var isLoaded = false
    protected var mRootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(setLayoutId(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener(this)
        val activity: FragmentActivity? = activity
        if (activity != null) {
            mContext = activity
        }
        view.setBackgroundResource(windowBackground)
        initView(view)
    }

    /**
     * 更换背景颜色
     *
     * @return
     */
    protected val windowBackground: Int
        get() = androidx.appcompat.R.color.abc_background_cache_hint_selector_material_light

    /**
     * 重新刷新界面
     */
    fun onReloadData() {}

    /**
     * 页面返回抽象函数
     */
    fun onBackPressed() {}
    override fun onResume() {
        super.onResume()
        //延迟加载走完加载页面的动画
        innerLoadData()
    }

    private fun innerLoadData() {
        if (!isLoaded || supportReload()) {
            isLoaded = true
            argument
            initData()
        }
    }

    protected fun supportReload(): Boolean {
        return false
    }

    fun reload(data: T) {}

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract fun setLayoutId(): Int

    /**
     * view与数据绑定
     */
    protected abstract fun initView(view: View?)
    protected val argument: Unit
        protected get() {}

    /**
     * 初始化数据
     */
    protected abstract fun initData()


    override fun onClick(v: View) {}
    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    /**
     * 页面埋点所需要页面名称
     *
     * @return 页面名称
     */
    protected val routeName: String
        protected get() = javaClass.simpleName

    /**
     * 页面埋点所需要页面类名
     *
     * @return 页面类名
     */
    protected val routeClass: String
        protected get() = javaClass.toString()

    /**
     * 页面埋点上报控制
     * 默认上报所有页面开启
     *
     * @return 页面类名
     */
    protected val isTrackRoute: Boolean
        protected get() = true

    /**
     * 页面停留时间上报控制
     *
     * @return
     */
    protected val isTrackRouteStayTime: Boolean
        protected get() = false


    fun applySkin() {}
}