package com.example.kawka.myfly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;



public class MPlanLotyFragment extends Fragment {

    View myView;

    public int a = 22 ;
    SeekBar seekBar;

    private static final String[] topTitles = new String[] {"T startu", "T lądowania", "Nr ćwiczenia", "Typ SP"};
    private static final String[] leftTitles = new String[] {" ", " ", " "};





    private ScrollTableView stv_wariant1, stv_wariant2, stv_wariant3, stv_wariant4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mplan_loty, container, false);


        stv_wariant1 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant1);
        stv_wariant2 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant2);
        stv_wariant3 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant3);
        stv_wariant4 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant4);


        ArrayList<String> topTitles = createTopTitles();


        stv_wariant1.setDatas(createTopTitles(), createLeftTitles(), createContent(3, topTitles.size()));
        stv_wariant2.setDatas(createTopTitles(), createLeftTitles(), createContent(3, topTitles.size()));
        stv_wariant3.setDatas(createTopTitles(), createLeftTitles(), createContent(3, topTitles.size()));
        stv_wariant4.setDatas(createTopTitles(), createLeftTitles(), createContent(3, topTitles.size()));


        return myView;
    }






    private ArrayList<ArrayList<String>> createContent(int row, int column) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                strings.add( i + "" + j);
            }
            results.add(strings);
        }
        return results;
    }



    private ArrayList<String> createTopTitles() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitles) {
            results.add(string);
        }
        return results;
    }

    private ArrayList<String> createLeftTitles() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitles) {
            results.add(string);
        }
        return results;
    }





}





