package com.zl.data.respository;

import com.zl.data.model.CookDetailsResult;
import com.zl.data.model.CookList;

/**
 * Created by user on 2016/8/9.
 */
public interface CookListRepository {
    CookList getCookList(int page,int rows,int id);

    CookDetailsResult getCookDetails(int id);
}
