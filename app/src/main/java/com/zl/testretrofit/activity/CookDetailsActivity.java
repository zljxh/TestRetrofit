package com.zl.testretrofit.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zl.data.model.CookDetailsResult;
import com.zl.data.respository.impl.CookListRepositoryImpl;
import com.zl.testretrofit.R;
import com.zl.testretrofit.presentation.presenters.impl.CookDetailsPresenterImpl;
import com.zl.testretrofit.utils.CircleTransform;
import com.zl.testretrofit.utils.PicassoRoundTransform;

/**
 * Created by user on 2016/8/10.
 */
public class CookDetailsActivity extends BaseActivity implements CookDetailsPresenterImpl.View, Toolbar.OnMenuItemClickListener {
    private CookDetailsPresenterImpl presenter;
    private ImageView icon;
    private TextView tv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookdetails);
        initView();
        showProgressBar();
        doParms(getIntent().getIntExtra("id", 0));
    }

    private void initView() {
        icon = (ImageView) findViewById(R.id.icon);
        tv = (TextView) findViewById(R.id.content);

        toolbar = (Toolbar) findViewById(R.id.toolbal);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.wihte));
        toolbar.setNavigationIcon(this.getResources().getDrawable(R.mipmap.back_icon));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doParms(int id) {
        presenter = new CookDetailsPresenterImpl(mExecutor,
                mMainThread,
                this,
                new CookListRepositoryImpl());
        presenter.getCookDetails(id);
    }

    @Override
    public void disPalyCookDetail(CookDetailsResult result) {
        disMissProgressBar();
        Picasso.with(this).load("http://tnfs.tngou.net/image" + result.img).transform(new PicassoRoundTransform()).into(icon);
        tv.setText(Html.fromHtml(result.message));
        toolbar.setTitle(result.name);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
