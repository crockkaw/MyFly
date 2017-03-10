/*
 * Copyright (C) 2016 Nandan Kaushik Dutta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codevscolor.materialpreference.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;

import com.codevscolor.materialpreference.R;
import com.codevscolor.materialpreference.activity.MaterialMainActivity;
import com.codevscolor.materialpreference.util.MaterialPrefUtil;


public class MaterialPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Context myContext;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        myContext = this.getActivity();

        myContext.setTheme(getTheme(MaterialPrefUtil.getSecondaryColorPosition() + 1));

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(getResources().getIdentifier(MaterialPrefUtil.getXmlResourceName(), "xml", MaterialPrefUtil.getAppPackageName()));

        /// code here:

        SwitchPreferenceCompat prefShortLog;
        CheckBoxPreference  prefPin, prefFinger;

        prefShortLog = (SwitchPreferenceCompat) findPreference("switch_short_login");
        prefPin = (CheckBoxPreference) findPreference("Checkbox_pin");
        prefFinger = (CheckBoxPreference) findPreference("Checkbox_finger");

        prefShortLog.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                boolean checked = Boolean.valueOf(newValue.toString());

                if (checked==false){
                    getPreferenceScreen().findPreference("Checkbox_pin").setEnabled(false);
                    getPreferenceScreen().findPreference("Checkbox_finger").setEnabled(false);
                    getPreferenceScreen().findPreference("changePin").setEnabled(false);
                    getPreferenceScreen().findPreference("changeFinger").setEnabled(false);
                }
                if (checked==true){
                    getPreferenceScreen().findPreference("Checkbox_pin").setEnabled(true);
                    getPreferenceScreen().findPreference("Checkbox_finger").setEnabled(true);
                    getPreferenceScreen().findPreference("changePin").setEnabled(true);
                    getPreferenceScreen().findPreference("changeFinger").setEnabled(true);
                }
                return true;
            }
        });

        prefPin.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                boolean checked = Boolean.valueOf(newValue.toString());

                if (checked==false){
                    getPreferenceScreen().findPreference("changePin").setEnabled(false);
                }
                if (checked==true){
                    getPreferenceScreen().findPreference("changePin").setEnabled(true);
                }
                return true;
            }
        });


        prefFinger.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                boolean checked = Boolean.valueOf(newValue.toString());

                if (checked==false){
                    getPreferenceScreen().findPreference("changeFinger").setEnabled(false);
                }
                if (checked==true){
                    getPreferenceScreen().findPreference("changeFinger").setEnabled(true);
                }
                return true;
            }
        });

    }


    private int getTheme(int position) {
        switch (position) {
            case 1:
                return R.style.settingsTheme1;

            case 2:
                return R.style.settingsTheme2;

            case 3:
                return R.style.settingsTheme3;

            case 4:
                return R.style.settingsTheme4;

            case 5:
                return R.style.settingsTheme5;

            case 6:
                return R.style.settingsTheme6;

            case 7:
                return R.style.settingsTheme7;

            case 8:
                return R.style.settingsTheme8;

            case 9:
                return R.style.settingsTheme9;

            case 10:
                return R.style.settingsTheme10;

            case 11:
                return R.style.settingsTheme11;

            case 12:
                return R.style.settingsTheme12;

            case 13:
                return R.style.settingsTheme13;

            case 14:
                return R.style.settingsTheme14;

            case 15:
                return R.style.settingsTheme15;

            case 16:
                return R.style.settingsTheme16;

            case 17:
                return R.style.settingsTheme17;

            case 18:
                return R.style.settingsTheme18;

            case 19:
                return R.style.settingsTheme19;

            default:
                return R.style.settingsTheme1;

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        ((MaterialMainActivity)myContext).notifyPreferenceChanged(sharedPreferences,s);
        if (s.equals(MaterialPrefUtil.getSecondaryColorKey())) {
            Intent intent = new Intent(getActivity(), MaterialMainActivity.class);
            startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(0,0);

        }
    }
}