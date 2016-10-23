package com.zl.data.service;

import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.HttpResult;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 2016/8/10.
 */
public interface CookDetailService  {
    public static final String COOKDETAIL="/api/cook/show";

    @POST(COOKDETAIL)
    Call<CookDetailsResult> getCookDetailsResult(@Query("id") long id);
}
