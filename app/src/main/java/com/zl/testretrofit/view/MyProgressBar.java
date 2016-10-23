package com.zl.testretrofit.view;


import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.testretrofit.R;


/**
 * Created by user on 2016/8/8.
 */
public class MyProgressBar extends Dialog {
    private TextView msg;
    private ImageView image;
    private RotateAnimation refreshingAnimation;


    public MyProgressBar(Context context) {
        super(context);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);


        init(context);
    }

    public MyProgressBar(Context context, int themeResId) {
        super(context, themeResId);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        init(context);
    }

    protected MyProgressBar(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        init(context);

    }

    public MyProgressBar(Context context, int width, int height, View layout, int style) {

        super(context, style);

        setContentView(layout);

        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();

        params.gravity = Gravity.CENTER;

        window.setAttributes(params);

    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.progress_bar, null);
        msg = (TextView) v.findViewById(R.id.msg);
        image = (ImageView) v.findViewById(R.id.image);
        LinearInterpolator lir = new LinearInterpolator();
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        refreshingAnimation.setInterpolator(lir);
        image.startAnimation(refreshingAnimation);
        super.setContentView(v);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    public void setMsg(String msg) {
//        this.msg.setText(msg);
    }
}
