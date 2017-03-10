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

package com.codevscolor.materialpreference.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

/**
 * Created by nandan on 14/03/16.
 * Utility class for handling color and themes
 */
public class MaterialPrefUtil {

    //use dark theme or not
    private static boolean isDarkThemeEnable = false;

    //primary color position
    private static int mPrimaryColorPosition = 0;

    //secondary color position
    private static int mSecondaryColorPosition = 0;

    //preference resource file name
    private static String mXmlResName = null;

    //application package name
    private static String mPackageName = null;

    private static String mSecondaryColorKey;

    //color lists
    public static final int COLOR_RED = 0;
    public static final int COLOR_PINK = 1;
    public static final int COLOR_PURPLE = 2;
    public static final int COLOR_DEEP_PURPLE = 3;
    public static final int COLOR_INDIGO = 4;
    public static final int COLOR_BLUE = 5;
    public static final int COLOR_LIGHT_BLUE = 6;
    public static final int COLOR_CYAN = 7;
    public static final int COLOR_TEAL = 8;
    public static final int COLOR_GREEN = 9;
    public static final int COLOR_LIGHT_GREEN = 10;
    public static final int COLOR_LIME = 11;
    public static final int COLOR_YELLOW = 12;
    public static final int COLOR_AMBER = 13;
    public static final int COLOR_ORANGE = 14;
    public static final int COLOR_DEEP_ORANGE = 15;
    public static final int COLOR_BROWN = 16;
    public static final int COLOR_GREY = 17;
    public static final int COLOR_BLUE_GREY = 18;

    /**
     * use current dark theme
     *
     * @param flag : true to enable dark theme
     */
    public static void useDarkTheme(boolean flag) {
        isDarkThemeEnable = flag;
    }


    /**
     * getter method to check if dark theme is enabled
     *
     * @return : true or false
     */
    public static boolean isDarkThemeEnable() {
        return isDarkThemeEnable;
    }

    /**
     * set primary color position
     *
     * @param position : position for primary color
     */
    public static void setPrimaryColorPosition(int position) {
        mPrimaryColorPosition = position;
    }

    /**
     * set secondary color position
     *
     * @param position : position for secondary color
     */
    public static void setSecondaryColorPosition(Context context, int position) {
        final SharedPreferences prefs = context.getSharedPreferences("materialpreference", Context.MODE_PRIVATE);
        if (!prefs.contains("colorPosition")) {
            mSecondaryColorPosition = position;
        } else
            mSecondaryColorPosition = prefs.getInt("colorPosition", 0);
    }

    public static void setSecondaryColorPosition(int position) {
        mSecondaryColorPosition = position;
    }

    /**
     * get primary color position
     *
     * @return : primary color position int value
     */
    public static int getPrimaryColorPosition() {
        return mPrimaryColorPosition;
    }


    /**
     * get secondary color position
     *
     * @return : secondary color position int value
     */
    public static int getSecondaryColorPosition() {
        return mSecondaryColorPosition;
    }

    /**
     * set xml resource name
     *
     * @param name : name for xml preference resource
     */
    public static void setXmlResourceName(String name) {
        mXmlResName = name;
    }

    public static void setSecondaryColorKey(String key){
        mSecondaryColorKey = key;
    }

    public static String getSecondaryColorKey(){
        return mSecondaryColorKey;
    }
    /**
     * set parent application package name
     *
     * @param name : package name
     */
    public static void setAppPackageName(String name) {
        mPackageName = name;
    }

    /**
     * get xml resource name
     *
     * @return : xml resource string
     */
    public static String getXmlResourceName() {
        return mXmlResName;
    }


    /**
     * get parent application package name
     *
     * @return : package name string
     */
    public static String getAppPackageName() {
        return mPackageName;
    }

    public static void updateColor(int position) {
        mSecondaryColorPosition = position;
    }


    public static int getSecondaryColor() {
        return Color.parseColor(MaterialPrefUtil.secondaryColor[MaterialPrefUtil.mSecondaryColorPosition]);
    }

    public final static String[] primaryColor = new String[]{
            //500
            "#F44336",//red
            "#E91E63",//pink
            "#9C27B0",//purple
            "#673AB7",//Deep purple
            "#3F51B5",//Indigo
            "#2196F3",//blue
            "#03A9F4",//light blue
            "#00BCD4",//cyan
            "#009688",//teal
            "#4CAF50",//green
            "#8BC34A",//light green
            "#CDDC39",//lime
            "#FFEB3B",//yellow
            "#FFC107",//amber
            "#FF9800",//orange
            "#FF5722",//deep orange
            "#795548",//brown
            "#9E9E9E",//grey
            "#607D8B"//blue grey
    };

    public final static String[] primaryColorDark = new String[]{
            //700
            "#D32F2F",//red
            "#C2185B",//pink
            "#7B1FA2",//purple
            "#512DA8",//Deep purple
            "#303F9F",//Indigo
            "#1976D2",//blue
            "#0288D1",//light blue
            "#0097A7",//cyan
            "#00796B",//teal
            "#388E3C",//green
            "#689F38",//light green
            "#AFB42B",//lime
            "#FBC02D",//yellow
            "#FFA000",//amber
            "#F57C00",//orange
            "#E64A19",//deep orange
            "#5D4037",//brown
            "#616161",//grey
            "#455A64"//blue grey
    };


    public final static String[] secondaryColor = new String[]{
            //500
            "#F44336",//red
            "#E91E63",//pink
            "#9C27B0",//purple
            "#673AB7",//Deep purple
            "#3F51B5",//Indigo
            "#2196F3",//blue
            "#03A9F4",//light blue
            "#00BCD4",//cyan
            "#009688",//teal
            "#4CAF50",//green
            "#8BC34A",//light green
            "#CDDC39",//lime
            "#FFEB3B",//yellow
            "#FFC107",//amber
            "#FF9800",//orange
            "#FF5722",//deep orange
            "#795548",//brown
            "#9E9E9E",//grey
            "#607D8B"//blue grey
    };

    public final static String[] textColorDark = new String[]{
            //primary , secondary and hint
            "#D9000000",
            "#8C000000", "#4D000000"

    };

    public final static String[] textColorLight = new String[]{
            //primary , secondary and hint
            "#D9FFFFFF",
            "#8CFFFFFF", "#4DFFFFFF"

    };

}
