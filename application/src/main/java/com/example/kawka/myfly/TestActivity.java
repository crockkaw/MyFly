package com.example.kawka.myfly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;

public class TestActivity extends AppCompatActivity {

    String pinPass;
    String PASSWORD_PREFERENCE_KEY, PASSWORD_SALT_PREFERENCE_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);



        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        pinPass = mSharedPreferences.getString(PASSWORD_SALT_PREFERENCE_KEY, null);

        if (pinPass != null && !pinPass.isEmpty()) {
            normalLog();
        }  else {
            firstLog();
        }

//        mSharedPreferences.getString(PASSWORD_SALT_PREFERENCE_KEY, null);
//
//
//
//        if(pinPass != null && !pinPass.isEmpty()) {
//            normalLog();
//        } else {
//            firstLog();
//        }
    }



    public void firstLog() {
        Intent intent = new Intent(getApplication(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void normalLog() {
        Intent intent = new Intent(getApplication(), PinLockActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
            }
}
