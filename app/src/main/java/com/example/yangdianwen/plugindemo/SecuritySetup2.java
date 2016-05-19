package com.example.yangdianwen.plugindemo;

import android.os.Bundle;

public class SecuritySetup2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_setup2);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void nextActivity() {

        start(SecuritySetup3.class);
    }

    @Override
    public void previousActivity() {
        start(SecuritySetup1.class);
    }
}
