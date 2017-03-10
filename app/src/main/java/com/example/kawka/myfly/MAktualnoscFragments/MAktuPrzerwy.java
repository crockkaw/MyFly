package com.example.kawka.myfly.MAktualnoscFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kawka.myfly.R;


public class MAktuPrzerwy extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_maktu_przerwy, container ,false);


        return myView;
    }
}

//    Intent intent = new Intent(this,IdentificationActivity.class);
//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        thread.interrupt();
//        startActivity(intent);
