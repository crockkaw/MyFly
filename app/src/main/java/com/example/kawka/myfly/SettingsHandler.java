package com.example.kawka.myfly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;

public class SettingsHandler extends AppCompatActivity {

    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        a = bundle.getString("type");

        if (a.equals("pin")){
            Intent intent = new Intent(getApplication(), PinLockActivity.class);
            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        if (a.equals("finger")){
            Intent intent = new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }


    }
}
