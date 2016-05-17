package com.example.yangdianwen.plugindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**这是自定义SharedPreferences工具类保存数据
 * Created by yangdianwen on 2016/5/16.
 */

public class spHelper {
    public static final String NAME = "PluginDemo";
    private static SharedPreferences msp;

    //
    public  static void putString(Context context,String key,String value){
        SharedPreferences sp=getPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        //把密码添加到编辑器中
        editor.putString(key, value);
        //提交数据
        editor.apply();

    }
    private static SharedPreferences getPreferences(Context context) {
        if (msp==null){
             msp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        }
        return msp;
    }
    //获取数据的方法
    public static String getString(Context context, String key) {
    return  getString(context,key,null);
    }
    private  static String getString(Context context, String key, String value) {
        SharedPreferences sp=getPreferences(context);
        return sp.getString(key,value);
    }
}


