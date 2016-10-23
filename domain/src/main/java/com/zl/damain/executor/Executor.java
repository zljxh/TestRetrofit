package com.zl.damain.executor;

import com.zl.damain.interactors.base.AbstractInteractor;

/**
 * Created by user on 2016/8/9.
 */
public interface Executor {
    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
