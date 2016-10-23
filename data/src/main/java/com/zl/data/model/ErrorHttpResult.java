package com.zl.data.model;

/**
 * Created by user on 2016/8/11.
 */
public class ErrorHttpResult extends HttpResult {

    public ErrorHttpResult(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ErrorHttpResult(String errorMessage) {
        super(errorMessage);
    }


}
