package com.zl.testretrofit.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.data.model.CookList;
import com.zl.data.model.HttpListResult;
import com.zl.data.model.HttpResult;
import com.zl.data.model.Tngou;
import com.zl.data.respository.impl.CookListRepositoryImpl;
import com.zl.data.service.ListService;
import com.zl.testretrofit.R;
import com.zl.testretrofit.adapter.MainAdapter;
import com.zl.testretrofit.presentation.presenters.impl.CookListPresenterImple;
import com.zl.testretrofit.view.PullToRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements CookListPresenterImple.View, PullToRefreshLayout.OnLoadListener {
    private CookListPresenterImple presenterImple;
    private ListView listview;
    private MainAdapter adapter;
    private ArrayList<Tngou> list = new ArrayList<>();
    private int page = 1;
    private int pageSize = 20;
    private PullToRefreshLayout refreshLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showProgressBar();
        initParams();

    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbal);
        toolbar.setTitle("健康食谱");
        toolbar.inflateMenu(R.menu.searchmenu);
//        SearchView searchView= (SearchView) toolbar.getMenu().findItem(0).getActionView();
//        searchView.setQueryHint("搜索搜索");
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.search));
        searchView.setQueryHint("搜索搜索");

        SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);

        ImageView delete= (ImageView) searchView.findViewById(R.id.search_close_btn);
        delete.setImageResource(R.mipmap.approve_no);


        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.WHITE);
        ImageView icTip = (ImageView) searchView.findViewById(R.id.search_mag_icon);
        icTip.setImageResource(R.mipmap.back_icon);


        toolbar.setTitleTextColor(this.getResources().getColor(R.color.wihte));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, com.zl.testretrofit.utils.Imageview.MainActivity.class);
                intent.putExtra("id", list.get(i).id);
                startActivity(intent);
            }
        });

        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnloadListener(this);
    }

    private void initParams() {
        if (presenterImple == null) {
            presenterImple = new CookListPresenterImple(
                    mExecutor,
                    mMainThread,
                    this,
                    new CookListRepositoryImpl()
            );
        }

        presenterImple.getCookList(page, pageSize, 0);
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
    public void disPalyCookList(ArrayList<Tngou> list, boolean isfailuer) {
        disMissProgressBar();
        if (isfailuer) {
            refreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
            showError("请求失败");
            Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("zl", "执行这里啦disPalyCookList");
            refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            this.list.addAll(list);
            initAdapter();
        }
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new MainAdapter(this, list);
            listview.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        page++;
        initParams();
    }
}
