package com.zl.testretrofit.presentation.presenters.impl;

import android.util.Log;
import android.view.View;

import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;
import com.zl.damain.interactors.cooklist.CookListInteractor;
import com.zl.damain.interactors.cooklist.impl.CookListInteractorImpl;
import com.zl.data.model.CookList;
import com.zl.data.model.HttpResult;
import com.zl.data.model.Tngou;
import com.zl.data.respository.CookListRepository;
import com.zl.testretrofit.presentation.presenters.base.AbstractPresenter;
import com.zl.testretrofit.presentation.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by user on 2016/8/9.
 */
public class CookListPresenterImple extends AbstractPresenter implements CookListInteractor.Callback {
    private View view;
    private CookListRepository repository;

    public CookListPresenterImple(Executor executor, MainThread mainThread, View view, CookListRepository repository) {
        super(executor, mainThread);
        this.view = view;
        this.repository = repository;
    }

    public void getCookList(int page,int rows,int id){
        CookListInteractor interactor=new CookListInteractorImpl(
                mExecutor,mMainThread,
                this,
                repository,
                CookListInteractor.GET_COOKLIST,
                page,rows,id
        );
        interactor.execute();
    }

    @Override
    public <T> void onComplete(int actionId, T result) {
        if (actionId==CookListInteractor.GET_COOKLIST){
            CookList cookList= (CookList) result;
            Log.i("jxh","执行到这里啦");
            Log.i("zl","size="+cookList.tngou.size());
            view.disPalyCookList(cookList.tngou,false);
        }
    }

    @Override
    public void onFailuer(int actionId, HttpResult httpResult) {
        if (actionId==CookListInteractor.GET_COOKLIST){
            view.disPalyCookList(null,true);
        }
    }


    public interface View extends BaseView {
        void disPalyCookList(ArrayList<Tngou> list,boolean isfailuer);
    }
}
