package com.wtt.baseproject.test

import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.wtt.baselib.BaseActivity
import com.wtt.baseproject.R


class LottieActivity : BaseActivity() {
    private lateinit var animationView: LottieAnimationView

    override fun layoutID(): Int {
        return R.layout.activity_lottie
    }

    override fun initView() {
        animationView = findViewById<View>(R.id.animation_view) as LottieAnimationView
        findViewById<View>(R.id.startAnim).setOnClickListener(this)
        findViewById<View>(R.id.endAnim).setOnClickListener(this)
        animationView.setAnimation("gu zhang2.json")
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.startAnim -> animationView.playAnimation()
            R.id.endAnim -> animationView.cancelAnimation()

        }
    }

}