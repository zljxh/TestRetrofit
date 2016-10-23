package com.zl.damain.interactors.cooklist;

import com.zl.damain.interactors.base.Interactor;
import com.zl.data.model.HttpResult;

/**
 * Created by user on 2016/8/9.
 */
public interface CookListInteractor extends Interactor {
    public static final int GET_COOKLIST = 1;
    public static final int GET_COOKDETAILS = 2;

    interface Callback {
        <T> void onComplete(int actionId, T result);

        void onFailuer(int actionId, HttpResult httpResult);
    }
}
