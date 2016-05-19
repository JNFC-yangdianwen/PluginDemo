package com.example.yangdianwen.plugindemo;

import android.os.Bundle;

public class SecuritySetup extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_setup);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void nextActivity() {

        start(SecuritySetup1.class);
    }

    @Override
    public void previousActivity() {

    }


}
