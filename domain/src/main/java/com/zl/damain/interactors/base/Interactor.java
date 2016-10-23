package com.zl.damain.interactors.base;

/**
 * Created by user on 2016/8/9.
 */
public interface Interactor {
    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
