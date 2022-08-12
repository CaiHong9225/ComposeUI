package com.wtt.baselib.android;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.CountDownLatch;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;

/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
public class AsyncLayoutLoader {
    private int mLayoutId;
    private View mRealView;
    private Context mContext;
    private ViewGroup mRootView;
    private CountDownLatch mCountDownLatch;
    private AsyncLayoutInflaterPlus mInflater;
    private static SparseArrayCompat<AsyncLayoutLoader> sArrayCompat = new SparseArrayCompat<AsyncLayoutLoader>();

    public static AsyncLayoutLoader getInstance(Context context) {
        return new AsyncLayoutLoader(context);
    }

    public AsyncLayoutLoader(Context context) {
        this.mContext = context;
        mCountDownLatch = new CountDownLatch(1);
    }

    public void inflate(@LayoutRes int resid, @Nullable ViewGroup parent) {
        inflate(resid, parent, null);
    }

    public void inflate(@LayoutRes int resid, @Nullable ViewGroup parent, AsyncLayoutInflaterPlus.OnInflateFinishedListener listener) {
        mRootView = parent;
        mLayoutId = resid;
        sArrayCompat.append(mLayoutId, this);
        if (listener == null) {
            listener = (view, resid1, parent1) -> {
                mRealView = view;
            };
        }
        mInflater = new AsyncLayoutInflaterPlus(mContext);
        mInflater.inflate(resid, parent, mCountDownLatch, listener);
    }

    /**
     * getLayoutLoader 和 getRealView 方法配对出现
     * 用于加载和获取View在不同类的场景
     *
     * @param resid
     * @return
     */
    public static AsyncLayoutLoader getLayoutLoader(int resid) {
        return sArrayCompat.get(resid);
    }

    public View getRealView() {
        //出现异常 未能加载成功
        if (mRealView == null && !mInflater.isRunning()) {
            mInflater.cancel();
            //同步执行加载xml
            inflateSync();
        } else if (mRealView == null) {
            //等待加载xml
            try {
                mCountDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setLayoutParamByParent(mContext, mRootView, mLayoutId, mRealView);
        } else {
            setLayoutParamByParent(mContext, mRootView, mLayoutId, mRealView);
        }
        return mRealView;
    }

    /**
     * 根据Parent设置异步加载View的LayoutParamsView
     *
     * @param context
     * @param parent
     * @param layoutId
     * @param realView
     */
    private void setLayoutParamByParent(Context context, ViewGroup parent, int layoutId, View realView) {
        if (parent == null) {
            return;
        }
        XmlResourceParser parser = context.getResources().getLayout(layoutId);
        try {
            AttributeSet attributeSet = Xml.asAttributeSet(parser);
            ViewGroup.LayoutParams layoutParams = parent.generateLayoutParams(attributeSet);
            realView.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parser.close();
        }
    }


    //直接系统加载
    private void inflateSync() {
        mRealView = LayoutInflater.from(mContext).inflate(mLayoutId, mRootView, false);
    }
}
