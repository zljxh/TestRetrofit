package com.zl.data.model;

/**
 * Created by user on 2016/8/9.
 */
public class HttpResult {
    public Number check_session_status;
    public String message;

    public String resultJson;

    //    public Number status;
    public Number ret;

    public int errorCode = OK;
    public String errorMessage;

    public static final int OK = 0;

    public static final int SESSION_ERROR_CODE = 302;
    public static final int CUSTOMED_ERROR_CODE = 999;
    public static final int NORMAL_ERROR_CODE = 100;


    public HttpResult(){

    }

    public HttpResult(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpResult(String errorMessage){
        this.errorCode = CUSTOMED_ERROR_CODE;
        this.errorMessage = errorMessage;
    }

}
