package com.example.kawka.myfly;

import com.wangjie.androidinject.annotation.present.AISupportFragment;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;

/**
 * Created by kawka on 3/29/2017.
 */

public abstract class BaseFragment extends AISupportFragment {
    public abstract String getRfabIdentificationCode();

    public abstract String getTitle();

    public void onInitialRFAB(RapidFloatingActionButton rfab) {
    }

}