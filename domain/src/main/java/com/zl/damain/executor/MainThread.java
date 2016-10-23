package com.zl.damain.executor;

/**
 * Created by user on 2016/8/9.
 */
public interface MainThread {
    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);
}
