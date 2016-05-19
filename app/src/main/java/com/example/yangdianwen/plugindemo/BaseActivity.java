package com.example.yangdianwen.plugindemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 定义一个抽象的Activity基类，
 * <p/>
 * Created by yangdianwen on 2016/5/18.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化视图
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();


    }

    public void initEvent() {

    }

    public void initData() {


    }

    protected abstract void initView();


    //点击下一步的方法
    public void next(View view) {
        nextActivity();
        nextAnimation();
    }

    ////点击上一步的方法
    public void previous(View view) {
        previousActivity();
        nextAnimation();
    }

    //定义的跳转动画，使用系统封装的
    public void nextAnimation() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //开启一个Activity的方法
    public void start(Class Activity) {
        Intent intent = new Intent(this, Activity);
        startActivity(intent);
        finish();
    }

    //跳转到下一个Activity的方法
    public abstract void nextActivity();

    //跳转到上一个Activity的方法
    public abstract void previousActivity();
}
