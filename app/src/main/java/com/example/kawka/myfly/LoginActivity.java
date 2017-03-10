package com.example.kawka.myfly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;


public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void skipIntoFigerprint(View view) {
        Intent intent = new Intent(this,FingerprintActivity.class);
        startActivity(intent);
    }

    public void skipIntoPin(View view) {
        Intent intent = new Intent(LoginActivity.this, PinLockActivity.class);

        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
        startActivity(intent);

    }
}
