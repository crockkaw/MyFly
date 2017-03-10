package com.example.kawka.myfly.MAktualnoscFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kawka.myfly.LegendaActivity;
import com.example.kawka.myfly.R;

/**
 * Created by kawka on 1/18/2017.
 */

public class MAktuStatus extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_maktu_status, container ,false);

        FloatingActionButton fab = (FloatingActionButton) myView.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//                alertDialogBuilder.setMessage("Legenda...");
//                alertDialogBuilder.setPositiveButton("ok",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {}
//
//                        });
//
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();

                Intent intent = new Intent(getActivity(),LegendaActivity.class);
                startActivity(intent);


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        return myView;
    }


}
