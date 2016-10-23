package com.zl.testretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zl.data.model.Tngou;
import com.zl.testretrofit.R;
import com.zl.testretrofit.utils.CircleTransform;

import java.util.ArrayList;

/**
 * Created by user on 2016/8/10.
 */
public class MainAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Tngou> list;

    public MainAdapter(Context context, ArrayList<Tngou> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<Tngou> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_cook, null);
            vh.icon = (ImageView) view.findViewById(R.id.icon);
            vh.title = (TextView) view.findViewById(R.id.title);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.title.setText(list.get(i).name);
//        Picasso.with(context).load("http://tnfs.tngou.net/image" + list.get(i).img).transform(new CircleTransform()).into(vh.icon);
        Picasso.with(context).load("http://tnfs.tngou.net/image" + list.get(i).img).into(vh.icon);
        return view;
    }

    class ViewHolder {
        public ImageView icon;
        public TextView title;
    }
}
