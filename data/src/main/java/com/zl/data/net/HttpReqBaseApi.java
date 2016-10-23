package com.zl.data.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zl.data.model.HttpListResult;
import com.zl.data.model.HttpResult;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016/8/9.
 */
public class HttpReqBaseApi {
    private static Context mContext;
    private final static String BASE_URL = "http://www.tngou.net";
    private static Retrofit retrofit;
    private static Retrofit download_retrofit;
    private OkHttpInterceptor okHttpInterceptor;
    public static final int DEFAULT_TIMEOUT = 20;

    static final class OkHttpInterceptor implements Interceptor {
        private volatile String host;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url();
            String host = this.host;
            Request.Builder builder = request.newBuilder();
            if (host != null) {
                HttpUrl newUrl = request.url().newBuilder()
                        .host(host)
                        .build();
                builder.url(newUrl);
            } else {
                builder.url(url);
            }


//            /**
//             *   appid: android
//             */
//            builder.addHeader(APPID_HEADER, APP_ID_ANDROID);
//
//            /**
//             * user agent
//             */
//            builder.addHeader("User-Agent", crateUserAgent());


//            builder.addHeader("User-Agent", crateUserAgent());
//
////            if (secretkey != null && sessionid != null){
//            String sessionHeader = getSessionidHeader(secretkey,sessionid);
//            if (sessionHeader != null){
//                builder.addHeader(SESSIONID_HEADER, sessionHeader);
//            }


            return chain.proceed(builder.build());
        }
    }

    public final static class Builder {


        public Builder setContent(final Context content) {
            mContext = content;
            return this;
        }

        public void build() {
            if (mContext == null)
                throw new RuntimeException("Context not set, please set context before building the Prefs instance.");
        }
    }

    public <T> T createServier(final Class<T> servier) {
        return retrofit.create(servier);

    }

    public <T> HttpResult executeHttp(Call<T> call) {
        Log.i("jxh", "执行这里啦executeHttp");
        HttpResult httpResult = null;
        try {

            retrofit2.Response<T> response = call.execute();
            int code = response.code();
            Log.i("jxh", "response.code=" + response.code());

            if (code == 401) {
                return new HttpResult(HttpResult.SESSION_ERROR_CODE, "session失效");
            } else if (code == 302) {
                return new HttpResult(HttpResult.SESSION_ERROR_CODE, "session失效");

            }
            if (response.isSuccessful()) {
                Log.i("jxh", "response.code=" + response.code());
                Log.i("jxh", "response=" + response.body());
                if (!(response.body() instanceof List)) {
                    httpResult = (HttpResult) response.body();
                    if (httpResult.check_session_status != null) {
                        // 提示出错信息,并发出广播
                        return new HttpResult(HttpResult.SESSION_ERROR_CODE, httpResult.errorMessage);
                    } else {
                        return httpResult;
                    }
                } else {
                    HttpListResult httplistResult = new HttpListResult();
                    httplistResult.dataList = response.body();
                    return httplistResult;
                }
            } else {
                Log.i("jxh", "请求异常1");
                return new HttpResult(HttpResult.CUSTOMED_ERROR_CODE, "请求异常");
            }

        } catch (IOException e) {
            Log.i("jxh", "请求异常2");
            e.printStackTrace();
            return new HttpResult(HttpResult.CUSTOMED_ERROR_CODE, "请求异常");
        }

    }

    private static class SingletonHolder {
        private static final HttpReqBaseApi INSTANCE = new HttpReqBaseApi();
    }

    public static HttpReqBaseApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private HttpReqBaseApi() {
        Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
        dispatcher.setMaxRequests(20);//最大的请求数量
        dispatcher.setMaxRequestsPerHost(1);//主机同一个时间，最大的请求数量


        okHttpInterceptor = new OkHttpInterceptor();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .dispatcher(dispatcher)
//                .connectionPool(new ConnectionPool(100,30, TimeUnit.SECONDS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();

//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd'T'HH;mm;ssZ")
//                .create();


//        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
//        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
////        httpClientBuilder.addInterceptor(okHttpInterceptor);
//        httpClientBuilder.addInterceptor(logging);
//
//        retrofit = new Retrofit.Builder()
//                .client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
    }
}
