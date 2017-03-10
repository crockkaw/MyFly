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

package com.codevscolor.materialpreference.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.ImageView;

import com.codevscolor.materialpreference.callback.ColorChooserCallback;
import com.codevscolor.materialpreference.R;
import com.codevscolor.materialpreference.adapter.ColorAdapter;
import com.codevscolor.materialpreference.util.MaterialPrefUtil;

import java.util.Arrays;
import java.util.List;

public class ColorChooserPreference extends Preference implements ColorChooserCallback {

    private AlertDialog mDialog;
    private final Context mContext;

    public ColorChooserPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorChooserPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWidgetLayoutResource(R.layout.pref_color_chooser);
        mContext = context;
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        //holder.itemView.setClickable(false); // disable parent click
        ImageView imageView = (ImageView) holder.findViewById(R.id.imageViewIcon);
        //noinspection StatementWithEmptyBody
        if (imageView != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();
            gradientDrawable.setColor(Color.parseColor(MaterialPrefUtil.secondaryColor[MaterialPrefUtil.getSecondaryColorPosition()]));

            imageView.setClickable(true); // enable custom view click

        } else {
        }
    }

    @Override
    protected void onClick() {
        super.onClick();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        ColorAdapter colorAdapter;
        List<String> arrayListColor = Arrays.asList(MaterialPrefUtil.secondaryColor);
        colorAdapter = new ColorAdapter(getContext(), arrayListColor, 0, ColorChooserPreference.this);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        GridView gridView = (GridView) inflater.inflate(R.layout.dialog_grid, null);
        gridView.setAdapter(colorAdapter);

        builder.setView(gridView);

        mDialog = builder.show();
    }


    @Override
    public void onColorChanged(int colorPosition) {
        persistInt(colorPosition);
        if (mDialog != null)
            mDialog.dismiss();

        final SharedPreferences prefs = mContext.getSharedPreferences("materialpreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("colorPosition", colorPosition);
        editor.commit();
    }
}
