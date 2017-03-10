package com.example.kawka.myfly.MAktualnoscFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kawka.myfly.R;




public class MAktualnoscFragment extends Fragment {

    View myView;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_maktu_podstawowe, container, false);




        return myView;

    }







}

