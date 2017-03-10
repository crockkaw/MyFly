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

package com.codevscolor.materialpreference.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.codevscolor.materialpreference.callback.ColorChooserCallback;
import com.codevscolor.materialpreference.R;
import com.codevscolor.materialpreference.util.MaterialPrefUtil;

import java.util.List;

public class ColorAdapter extends ArrayAdapter<String> {

    private final ColorChooserCallback mCallback;
    private final int secondary_position;

    public ColorAdapter(Context mContext, List<String> arrayList, int position, ColorChooserCallback callback) {
        super(mContext, R.layout.dialog_grid_item, arrayList);
        mCallback = callback;
        secondary_position = MaterialPrefUtil.getSecondaryColorPosition();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.dialog_grid_item, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        if(secondary_position == position){
            imageView.setImageResource(R.drawable.mpref_done);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();
        gradientDrawable.setColor(Color.parseColor(getItem(position)));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialPrefUtil.updateColor(position);
                notifyDataSetChanged();
                mCallback.onColorChanged(position);
            }
        });
        return rowView;
    }
}