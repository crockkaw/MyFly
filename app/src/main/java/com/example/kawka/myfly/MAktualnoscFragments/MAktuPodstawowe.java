package com.example.kawka.myfly.MAktualnoscFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;

import com.example.kawka.myfly.R;


/**
 * Created by kawka on 1/18/2017.
 */

public class MAktuPodstawowe extends Fragment {

    View myView;
    CardView mCardView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_maktu_podstawowe, container ,false);
        mCardView = (CardView) myView.findViewById(R.id.cardview);

        return myView;
    }


}
