package com.example.kawka.myfly;

import android.os.Process;
import android.view.View;

/**
 * Created by kawka on 23.07.2017.
 */



public class MyUndoListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        Process.killProcess(Process.myPid());



    }
}