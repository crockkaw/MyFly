package com.example.kawka.myfly;

import android.app.Application;
import android.content.Context;

/**
 * Created by kawka on 3/24/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MyApplication.context;
    }
}