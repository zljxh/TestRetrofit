package com.zl.testretrofit.presentation.presenters.impl;

import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;
import com.zl.damain.interactors.cooklist.CookListInteractor;
import com.zl.damain.interactors.cooklist.impl.CookListInteractorImpl;
import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.HttpResult;
import com.zl.data.respository.CookListRepository;
import com.zl.testretrofit.presentation.presenters.base.AbstractPresenter;
import com.zl.testretrofit.presentation.ui.BaseView;

/**
 * Created by user on 2016/8/10.
 */
public class CookDetailsPresenterImpl extends AbstractPresenter implements CookListInteractor.Callback {

    private View view;
    private CookListRepository repository;



    public interface View extends BaseView {
        void disPalyCookDetail(CookDetailsResult result);
    }

    public CookDetailsPresenterImpl(Executor executor, MainThread mainThread, View view, CookListRepository repository) {
        super(executor, mainThread);
        this.repository = repository;
        this.view = view;
    }

    public void getCookDetails(int id) {
        CookListInteractor interactor = new CookListInteractorImpl(mExecutor,
                mMainThread,
                this,
                repository,
                CookListInteractor.GET_COOKDETAILS,
                0,
                0,
                id);
        interactor.execute();
    }

    @Override
    public <T> void onComplete(int actionId, T result) {
        CookDetailsResult result1= (CookDetailsResult) result;
        view.disPalyCookDetail(result1);
    }

    @Override
    public void onFailuer(int actionId, HttpResult httpResult) {

    }
}
