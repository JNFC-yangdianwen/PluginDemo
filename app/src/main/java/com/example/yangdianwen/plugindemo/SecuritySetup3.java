package com.example.yangdianwen.plugindemo;

import android.os.Bundle;

public class SecuritySetup3 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_setup3);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void nextActivity() {

    }

@Override
public void previousActivity() {
        start(SecuritySetup2.class);
        }
}
