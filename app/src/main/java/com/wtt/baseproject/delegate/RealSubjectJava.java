package com.wtt.baseproject.delegate;

import com.wtt.mykotlin.delegate.Subject;
import com.wtt.mykotlin.delegate.SubjectDelegate;

import androidx.annotation.NonNull;

/**
 * Created by Wangzhan on 2022/1/12
 *
 * @descr
 */
class RealSubjectJava implements Subject {
    private SubjectDelegate delegate = new SubjectDelegate();

    @Override
    public void body() {
        delegate.body();
    }

    @NonNull
    @Override
    public String getType() {
        return delegate.getType();
    }
}
