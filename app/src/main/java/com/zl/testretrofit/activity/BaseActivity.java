package com.zl.testretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zl.damain.executor.Executor;
import com.zl.damain.executor.MainThread;
import com.zl.damain.executor.impl.MainThreadImpl;
import com.zl.damain.executor.impl.ThreadExecutor;
import com.zl.testretrofit.view.MyProgressBar;

/**
 * Created by user on 2016/8/9.
 */
public class BaseActivity extends AppCompatActivity {
    protected Executor mExecutor;
    protected MainThread mMainThread;
    private MyProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mExecutor = ThreadExecutor.getInstance();
        mMainThread = MainThreadImpl.getInstance();
    }

    protected void showProgressBar() {
        if (progressBar == null) {
            progressBar = new MyProgressBar(this);
        }
        progressBar.show();
    }

    protected void disMissProgressBar(){
        if (progressBar!=null){
            progressBar.dismiss();
            progressBar=null;
        }
    }

}
