package com.zl.data.service;

import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.CookList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 2016/8/9.
 */
public interface ListService {
    public static final String GET_COOK_LIST = "/api/cook/list";

    @POST(GET_COOK_LIST)
    Call<CookList> getCookList(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);


    public static final String COOKDETAIL="/api/cook/show";

    @POST(COOKDETAIL)
    Call<CookDetailsResult> getCookDetailsResult(@Query("id") long id);
}
