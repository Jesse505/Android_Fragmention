package com.github.lnframeworkdemo;

import android.app.Application;
import android.content.Context;


/**
 * 类描述
 * 创建者:tiny
 * 日期:17/10/30
 */

public class MyApplication extends Application {
    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
