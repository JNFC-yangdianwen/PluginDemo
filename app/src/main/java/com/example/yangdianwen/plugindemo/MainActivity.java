package com.example.yangdianwen.plugindemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "加密之后";
    private Button mBtn;
    private EditText mPwd1;
    private EditText mPwd2;
    private Button mBtn_1;
    private Button mBtn_2;
    private boolean isFirst = false;
    private View mView;
    private EditText mEdit_getPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn_plugin);
        mBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
      if (isFirst){
          getPwdDialog();
      }else {
          dialog();
          isFirst=true;
      }
    }

    //自定义dialog
    private void dialog() {
        //创建AlertDialog.Builder的对象builder，需传一个context参数
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //使用view对象加载自定义的dialog
        mView = View.inflate(getApplicationContext(), R.layout.dialog, null);
        //使用view去找mPwd1 mPwd2 mBtn_1 mBtn_2几个控件的id
        mPwd1 = (EditText) mView.findViewById(R.id.edt_pwd1);
        mPwd2 = (EditText) mView.findViewById(R.id.edt_pwd2);
        mBtn_1 = (Button) mView.findViewById(R.id.plug);
        mBtn_2 = (Button) mView.findViewById(R.id.btn_cancle);
        //使用buider对象的setView方法加载view
        builder.setView(mView);
        //创建AlertDialog的对象dialog，并使用builder的show方法传给dialog
        final AlertDialog dialog = builder.show();
        //mBtn_1的点击事件
        mBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw1 = mPwd1.getText().toString().trim();
                String pw2 = mPwd2.getText().toString().trim();
                //判断两次的输入是否为空，使用TextUtils工具类中的isEmpty方法，
                if (TextUtils.isEmpty(pw1) || TextUtils.isEmpty(pw2)) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //使用String类中的equals方法，判断两次所输密码是否相等
                if (!pw1.equals(pw2)) {
                    Toast.makeText(getApplicationContext(), "密码不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                //加密
                MD5Encoder MD5=new MD5Encoder();
                Log.d(TAG,MD5.encode(MD5.encode(pw1)));
                spHelper.putString(getApplicationContext(), Constans.pwd, pw1);
                Toast.makeText(getApplicationContext(), "密码保存成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //mBtn_2的点击事件
        mBtn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    //第二次登陆的dialog
    public void getPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(getApplicationContext(), R.layout.get, null);
        builder.setView(view).show();
        mEdit_getPwd = (EditText) view.findViewById(R.id.ed_getPwd);
        Button btn_getPwd = (Button) view.findViewById(R.id.btn_login);
        btn_getPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //输入密码
                String mStr = mEdit_getPwd.getText().toString().trim();
                //获取保存的数据
                String password=  spHelper.getString(getApplicationContext(), Constans.pwd);
                //使用TextUtils类中的isEmpty判断密码是否为空
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断密码是否正确
//                if (mStr.equals(password)) {
//                    Toast.makeText(getApplicationContext(),"密码正确",Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                }
                if (!TextUtils.isEmpty(password)){
                    //使用MD5加密
                    MD5Encoder MD5=new MD5Encoder();
                   String encode = MD5.encode(MD5.encode(password));
                    Log.d(TAG,encode);
                    if (encode.equals(password)){
                        Toast.makeText(getApplicationContext(),"密码正确",Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
    }
}
