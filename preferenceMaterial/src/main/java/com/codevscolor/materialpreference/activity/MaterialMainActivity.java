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

package com.codevscolor.materialpreference.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.codevscolor.materialpreference.R;
import com.codevscolor.materialpreference.fragment.MaterialPreferenceFragment;
import com.codevscolor.materialpreference.util.MaterialPrefUtil;


public class MaterialMainActivity extends BaseActivity {

    private Toolbar mToolbar;

    //toolbar title text
    private static String mToolbarTitle;

    //toolbar title text size
    private int mTextSize;


    /**
     * set title for the settings window toolbar
     *
     * @param toolbarTitle : toolbar title
     */
    public void setToolbarTitle(String toolbarTitle) {
        mToolbarTitle = toolbarTitle;
    }


    /**
     * set primary color. Primary color will be used for the
     * toolbar
     *
     * @param position : integer value of color defined in {@link MaterialPrefUtil}
     */
    public void setPrimaryColor(int position) {
        MaterialPrefUtil.setPrimaryColorPosition(position);
    }


    /**
     * set secondary color. Secondary color will be used for all preference widgets
     * and preference header view.
     *
     * @param position :: integer value of color defined in {@link MaterialPrefUtil}
     */
    public void setDefaultSecondaryColor(Context context, int position) {
        MaterialPrefUtil.setSecondaryColorPosition(context,position);
    }


    /**
     * set theme of settings screen
     */
    public void useDarkTheme(boolean flag) {
        MaterialPrefUtil.useDarkTheme(flag);
    }

    /**
     * set xml resource name
     *
     * @param name : name for xml preference resource
     */
    public void setXmlResourceName(String name) {
        MaterialPrefUtil.setXmlResourceName(name);
    }


    /**
     * set parent application package name
     *
     * @param name : package name
     */
    public void setAppPackageName(String name) {
        MaterialPrefUtil.setAppPackageName(name);
    }


    public void notifyPreferenceChanged(SharedPreferences preferences, String name) {
        super.notifyPreferenceChanged(preferences,name);
    }


    public void setColorPickerKey(String key) {
        MaterialPrefUtil.setSecondaryColorKey(key);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (MaterialPrefUtil.isDarkThemeEnable())
            setTheme(R.style.AppThemeDark);
        else
            setTheme(R.style.AppThemeLight);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mToolbar = (Toolbar) findViewById(R.id.materialpref_toolbar);

        mToolbar.setTitle(mToolbarTitle);
        mToolbar.setBackgroundColor(Color.parseColor(MaterialPrefUtil.primaryColor[MaterialPrefUtil.getPrimaryColorPosition()]));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(MaterialPrefUtil.primaryColorDark[MaterialPrefUtil.getPrimaryColorPosition()]));
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.materialpref_bodylayout, new MaterialPreferenceFragment())
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
