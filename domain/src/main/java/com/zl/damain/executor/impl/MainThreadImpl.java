package com.zl.damain.executor.impl;

import android.os.Handler;
import android.os.Looper;

import com.zl.damain.executor.MainThread;

/**
 * Created by user on 2016/8/9.
 */
public class MainThreadImpl implements MainThread {
    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl(){ mHandler = new Handler(Looper.getMainLooper());}

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance(){
        if(sMainThread == null ){
            sMainThread = new MainThreadImpl();
        }
        return sMainThread;
    }
}
