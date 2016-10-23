package com.zl.testretrofit.presentation.presenters.base;


import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;

/**
 * Created by user on 2016/8/9.
 */
public abstract class AbstractPresenter implements BasePresenter {

    protected Executor mExecutor;
    protected MainThread mMainThread;



    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
