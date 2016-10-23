package com.zl.data.respository.impl;

import android.util.Log;

import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.CookList;
import com.zl.data.net.HttpReqBaseApi;
import com.zl.data.respository.CookListRepository;
import com.zl.data.service.ListService;

import retrofit2.Call;

/**
 * Created by user on 2016/8/9.
 */
public class CookListRepositoryImpl implements CookListRepository {
    private ListService service;

    @Override
    public CookList getCookList(int page, int rows, int id) {
        CookList result = new CookList();
        Call<CookList> call = this.service.getCookList(page, rows, id);
        result = (CookList) HttpReqBaseApi.getInstance().executeHttp(call);
        return result;
    }

    @Override
    public CookDetailsResult getCookDetails(int id) {
        CookDetailsResult result = new CookDetailsResult();
        Call<CookDetailsResult> call = this.service.getCookDetailsResult(id);
        result = (CookDetailsResult) HttpReqBaseApi.getInstance().executeHttp(call);
        return result;
    }

    public CookListRepositoryImpl() {
        this.service = HttpReqBaseApi.getInstance().createServier(ListService.class);
    }


}
