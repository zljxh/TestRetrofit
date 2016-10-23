package com.zl.damain.interactors.cooklist.impl;

import android.util.Log;

import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;
import com.zl.damain.interactors.base.AbstractInteractor;
import com.zl.damain.interactors.cooklist.CookListInteractor;
import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.CookList;
import com.zl.data.model.ErrorHttpResult;
import com.zl.data.model.HttpListResult;
import com.zl.data.respository.CookListRepository;

/**
 * Created by user on 2016/8/9.
 */
public class CookListInteractorImpl extends AbstractInteractor implements CookListInteractor {
    private CookListRepository repository;
    private Callback callback;
    private int page, rows, id;
    private int actionId;

    public CookListInteractorImpl(Executor threadExecutor,
                                  MainThread mainThread,
                                  CookListInteractor.Callback callback,
                                  CookListRepository cookListRepository,
                                  int actionId,
                                  int page,
                                  int rows,
                                  int id) {
        super(threadExecutor, mainThread);
        this.page = page;
        this.id = id;
        this.rows = rows;
        this.actionId = actionId;
        this.repository = cookListRepository;
        this.callback = callback;
    }

    private <T> void onComplete(final T result) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onComplete(actionId, result);
            }
        });
    }

    private void onFaile() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailuer(actionId, new ErrorHttpResult("处理失败了,请重试"));
            }
        });
    }

    @Override
    public void run() {
        if (actionId == GET_COOKLIST) {
            Log.i("jxh", "repository.getCookList(page,rows,id)");
            CookList cookList = repository.getCookList(page, rows, id);
            if (cookList != null) {
                onComplete(cookList);
            } else {
                onFaile();
            }

        } else if (actionId == GET_COOKDETAILS) {
            CookDetailsResult result = repository.getCookDetails(id);
            if (result != null) {
                onComplete(result);
            } else {
                onFaile();
            }
        }
    }


}
