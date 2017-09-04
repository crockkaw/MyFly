package com.example.kawka.myfly;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;

import org.joda.time.DateTime;


public class MPlanLotyFragment extends Fragment {

    View myView;

    public int a = 22 ;
    SeekBar seekBar;

    private  String[] topTitles;
    private static final String[] leftTitles = new String[] {" ", " ", " "};

    TextView nalotTextView1,nalotTextView2,nalotTextView3,nalotTextView4;

    String[] row11 = new String[] {"08:40","09:55","-","W-3"};
    String[] row21 = new String[] {"11:10","11:35","4","W-3"};
    String[] row31 = new String[] {"13:25","14:10","2","MI-17"};

    String[] row12 = new String[] {"09:00","10:00","-","W-3"};
    String[] row22 = new String[] {"11:25","12:10","2","Mi-17"};
    String[] row32 = new String[] {"13:10","14:35","4","MI-17"};

    String[] row13 = new String[] {"08:15","09:55","1","W-3"};
    String[] row23 = new String[] {"11:10","11:35","3","MI-17"};
    String[] row33 = new String[] {"12:00","13:00","-","MI-17"};

    String[] row14 = new String[] {"08:30","09:30","-","MI-17"};
    String[] row24 = new String[] {"10:00","10:25","3","MI-17"};
    String[] row34 = new String[] {"11:00","14:40","1","W-3"};




    private ScrollTableView stv_wariant1, stv_wariant2, stv_wariant3, stv_wariant4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mplan_loty, container, false);

        topTitles = new String[] {getString(R.string.plan_loty1), getString(R.string.plan_loty2), getString(R.string.plan_loty3), getString(R.string.plan_loty4)};

        stv_wariant1 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant1);
        stv_wariant2 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant2);
        stv_wariant3 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant3);
        stv_wariant4 = (ScrollTableView) myView.findViewById(R.id.scroll_wariant4);


        ArrayList<String> topTitles = createTopTitles();


        stv_wariant1.setDatas(createTopTitles(), createLeftTitles(), createContent(row11,row21,row31));
        stv_wariant2.setDatas(createTopTitles(), createLeftTitles(), createContent(row12,row22,row32));
        stv_wariant3.setDatas(createTopTitles(), createLeftTitles(), createContent(row13,row23,row33));
        stv_wariant4.setDatas(createTopTitles(), createLeftTitles(), createContent(row14,row24,row34));

        nalotTextView1 = (TextView) myView.findViewById(R.id.nalotTextView1);
        nalotTextView2 = (TextView) myView.findViewById(R.id.nalotTextView2);
        nalotTextView3 = (TextView) myView.findViewById(R.id.nalotTextView3);
        nalotTextView4 = (TextView) myView.findViewById(R.id.nalotTextView4);



        DecimalFormat df = new DecimalFormat("00");

        java.util.Calendar instance = java.util.Calendar.getInstance();
        String dd = String.valueOf(df.format(instance.get(java.util.Calendar.DAY_OF_MONTH)));
        String mm = String.valueOf(df.format(instance.get(java.util.Calendar.MONTH)+1));
        String yy = String.valueOf(instance.get(java.util.Calendar.YEAR));
        String date = dd + "." + mm + "." + yy;

        String dd2 = String.valueOf(df.format(instance.get(java.util.Calendar.DAY_OF_MONTH)+1));
        String date2 = dd2 + "." + mm + "." + yy;



        nalotTextView1.setText("PTL WARIANT I / "+date);
        nalotTextView2.setText("PTL WARIANT II / "+date);
        nalotTextView3.setText("PTL WARIANT I / "+date2);
        nalotTextView4.setText("PTL WARIANT II / "+date2);

        return myView;
    }






    private ArrayList<ArrayList<String>> createContent(String[] row1, String[] row2, String[] row3 ) {


        ArrayList<ArrayList<String>> results = new ArrayList<>();
            ArrayList<String> strings1 = new ArrayList<>();
            for (String string : row1) {
                strings1.add(string);
            }
            results.add(strings1);

            ArrayList<String> strings2 = new ArrayList<>();
            for (String string : row2) {
                strings2.add(string);
            }
            results.add(strings2);

            ArrayList<String> strings3 = new ArrayList<>();
            for (String string : row3) {
                strings3.add(string);
            }
            results.add(strings3);


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





