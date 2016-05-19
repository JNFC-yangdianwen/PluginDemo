package com.example.yangdianwen.plugindemo;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SecuritySetup1 extends BaseActivity {
    private Button mBtn_bind;
    private ImageView mIv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //初始化视图
    @Override
    protected void initView() {
        setContentView(R.layout.activity_security_setup1);
        mBtn_bind = (Button) findViewById(R.id.btn_bind);
        mIv_pic = (ImageView) findViewById(R.id.image_lock);
        //为了防止加锁时的回显，需先判断TextUtils是否为空
        if (TextUtils.isEmpty(spHelper.getString(getApplicationContext(), Constans.pwd))) {
            mIv_pic.setImageResource(R.mipmap.my_unlock);
        } else {
            mIv_pic.setImageResource(R.mipmap.my_lock);
        }
    }

    //初始化事件
    @Override
    public void initEvent() {
        super.initEvent();
        mBtn_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断TextUtils是否为空，如果为空，加锁，使用SharedPreference储存数据，并且锁的状态改变。
                if (TextUtils.isEmpty(spHelper.getString(getApplicationContext(), Constans.pwd))) {
                    //获取SIM卡的唯一的id ssid，使用TelephonyManager类，调用系统的TELEPHONY_SERVICE
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String simSerialNumber = tm.getSimSerialNumber();
                    //储存数据
                    spHelper.putString(getApplicationContext(), Constans.pwd, simSerialNumber);
                    Toast.makeText(SecuritySetup1.this, "加锁成功", Toast.LENGTH_SHORT).show();
                    mIv_pic.setImageResource(R.mipmap.my_lock);
                } else {
                    spHelper.putString(getApplicationContext(), Constans.pwd, "");
                    Toast.makeText(SecuritySetup1.this, "解绑SIM卡", Toast.LENGTH_SHORT)
                            .show();
                    mIv_pic.setImageResource(R.mipmap.my_unlock);

                }

            }
        });
    }

    //跳转到下一个界面，
    @Override
    public void nextActivity() {
        //先判断是否绑定sim卡
        if (TextUtils.isEmpty(spHelper.getString(getApplicationContext(), Constans.pwd))) {
            Toast.makeText(SecuritySetup1.this, "请先绑定SIM卡再继续", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        start(SecuritySetup2.class);
    }

    @Override
    public void previousActivity() {
        start(SecuritySetup.class);
    }
}
