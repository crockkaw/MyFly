package com.example.kawka.myfly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.MenuItem;

import com.codevscolor.materialpreference.activity.MaterialPreferenceActivity;
import com.codevscolor.materialpreference.callback.MaterialPreferenceCallback;
import com.codevscolor.materialpreference.util.MaterialPrefUtil;


public class SettingsActivity extends MaterialPreferenceActivity implements MaterialPreferenceCallback {

    final static String ACTION_PREFS_ONE = "com.example.kawka.PREFS_ONE";


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //initialization of the sdk should be done here
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        //register this class as listener for preference change
        setPreferenceChangedListener(this);

        //use dark theme or not . Default is light theme
//        useDarkTheme(true);

        //set toolbar title
        setToolbarTitle("Ustawienia");

        //set primary color
        setPrimaryColor(MaterialPrefUtil.COLOR_BLUE_GREY);

        //default secondary color for tinting widgets, if no secondary color is used yet
        setDefaultSecondaryColor(this, MaterialPrefUtil.COLOR_BLUE);

        //set application package name and xml resource name of preference
        setAppPackageName("com.example.kawka.myfly");
        //set xml resource name
        setXmlResourceName("settingspreference");

        //optional
        //if you are using color picker, set the key used for color picker in the xml preference
        setColorPickerKey("secondary_color_position");

//        Preference userButton = (Preference) findPreference("pinChange");
//        userButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference arg0) {
//                Intent intent = new Intent(SettingsActivity.this, PinLockActivity.class);
//                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
//                startActivity(intent);
//                return true;
//            }
//        });


//        String action = getIntent().getAction();
//        if (action != null && action.equals(ACTION_PREFS_ONE)) {
//            Intent intent = new Intent(SettingsActivity.this, PinLockActivity.class);
//
//            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);        }


    }


    /**
     * callback for preference changes
     *
     * @param sharedPreferences
     * @param name
     */
    @Override
    public void onPreferenceSettingsChanged(SharedPreferences sharedPreferences, String name) {
//        Toast.makeText(this, name, Toast.LENGTH_LONG).show();


        if (name.equals("switch_short_login")) {
//            getPreferenceScreen().findPreference("Checkbox_true").setEnabled(true);
        }


//        if (name !="switch_pref"){
//            i();
//
//        }


//        String action = getIntent().getAction();
//        if (action != null && action.equals(ACTION_PREFS_ONE)) {
//            Intent intent = new Intent(SettingsActivity.this, PinLockActivity.class);
//
//            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);        }


    }
}
