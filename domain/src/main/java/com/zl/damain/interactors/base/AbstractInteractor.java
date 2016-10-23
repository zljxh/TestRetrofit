package com.zl.damain.interactors.base;

import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;

/**
 * Created by user on 2016/8/9.
 */
public abstract class AbstractInteractor implements Interactor {
    protected Executor mThreadExecutor;
    protected MainThread mainThread;
    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;


    public AbstractInteractor(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }

}
