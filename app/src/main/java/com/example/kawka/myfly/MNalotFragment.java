package com.example.kawka.myfly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import com.loopeer.android.librarys.scrolltable.ScrollTableView;


public class MNalotFragment extends Fragment  implements View.OnClickListener {

    View myView;

    public int a = 22;
    SeekBar seekBar;
    int wysokosc = 264;


    LinearLayout ll;

    Spinner spinRocznySp, spinRocznySym;



    ArrayList<ArrayList<String>> resultsAktualny;


    private  String[] topTitlesLoty ;
    private  String[] topTitlesRoczny ;
    private  String[] topTitlesAktualny ;

    private String[] leftTitles = new String[]{"W-3", "MI-17", "Razem"};
    private String[] leftTitlesSym = new String[]{"s. W-3", "s. MI-17", "Razem"};
    private String[] leftTitlesAktualny = new String[]{"W-3", "MI-17", "MI-2", "Razem"};


    String[] rowSp1 = new String[] {"7:40","6:50","8:20","6:30","10:50","12:20","14:40","13:20","0:00","08:10","07:30","06:40","112:50"};
    String[] rowSp2 = new String[] {"3:20","6:30","5:20","4:30","7:10","7:50","7:10","7:30","0:00","6:30","6:00","5:20","81:50"};
    String[] rowSp3 = new String[] {"11:00","13:20","13:40","11:00","18:00","20:10","21:50","20:50","0:00","14:40","13:30","12:00","193:40"};

    String[] rowSym1 = new String[] {"3:00","3:30","2:30","2:00","2:00","2:30","2:00","2:20","0:00","3:30","4:50","4:20","37:30"};
    String[] rowSym2 = new String[] {"3:30","2:30","2:00","3:00","2:00","2:20","2:00","2:30","0:00","3:00","3:40","2:50","31:20"};
    String[] rowSym3 = new String[] {"6:30","6:00","4:30","5:00","4:00","4:50","4:00","4:50","0:00","6:30","7:30","7:10","78:50"};

    String[] rowAktu1 = new String[] {"621:24","236:54","189:12","93:24", "102:15"};
    String[] rowAktu2 = new String[] {"458:32","132:15","148:25","81:10","73:20"};
    String[] rowAktu3 = new String[] {"268:48","92:30","94:15","32:10","28:05"};
    String[] rowAktu4 = new String[] {"1348:40","461:39","431:52","206:44","203:40"};


//    private String[] leftTitlesAktualny = new String[10];


    private ScrollTableView stv_loty, stv_aktualny, stv_roczny, stv_roczny_sym;
    Button dataButton1;
    int prog = 15;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mnalot, container, false);

        MainActivity activity = (MainActivity) getActivity();
//        leftTitlesAktualny = activity.nalotAktualnyAdapter.getMyLeftTitlesAktualny();
        resultsAktualny = activity.nalotAktualnyAdapter.getMyResultsAktualny();

        topTitlesLoty = new String[]{getString(R.string.nal_loty1), getString(R.string.nal_loty2), getString(R.string.nal_loty3), getString(R.string.nal_loty4), getString(R.string.nal_loty5), getString(R.string.nal_loty6)};
        topTitlesRoczny = new String[]{getString(R.string.nal_rocz1), getString(R.string.nal_rocz2), getString(R.string.nal_rocz3), getString(R.string.nal_rocz4), getString(R.string.nal_rocz5), getString(R.string.nal_rocz6), getString(R.string.nal_rocz7), getString(R.string.nal_rocz8), getString(R.string.nal_rocz9), getString(R.string.nal_rocz10), getString(R.string.nal_rocz11), getString(R.string.nal_rocz12), "Razem"};
        topTitlesAktualny = new String[]{getString(R.string.nal_cal1), getString(R.string.nal_cal2), getString(R.string.nal_cal3), getString(R.string.nal_cal4), getString(R.string.nal_cal5)};

        initialization();
        addItemsOnSpinner();

        return myView;
    }



    public void initialization() {

        dataButton1 = (Button) myView.findViewById(R.id.dataButton1);

        dataButton1.setOnClickListener(this);

        stv_loty = (ScrollTableView) myView.findViewById(R.id.scroll_t_loty);
        stv_aktualny = (ScrollTableView) myView.findViewById(R.id.scroll_t_aktualny);
        stv_roczny = (ScrollTableView) myView.findViewById(R.id.scroll_t_roczny);
        stv_roczny_sym = (ScrollTableView) myView.findViewById(R.id.scroll_t_roczny_sym);

        ArrayList<String> topTitlesLoty = createTopTitlesLoty();
        ArrayList<String> leftTitle = createLeftTitle();
        ArrayList<String> topTitlesRoczny = createTopTitlesRoczny();
        ArrayList<String> leftTitleLoty = createLeftTitleLoty();


        stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
        stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent1(rowSp1,rowSp2,rowSp3,null));
        stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitleSym(), createContent1(rowSym1,rowSym2,rowSym3, null));
        stv_aktualny.setDatas(createTopTitlesAktualny(), createLeftTitleAktualny(), createContent1(rowAktu1,rowAktu2,rowAktu3,rowAktu4));


//        stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
//        stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
//        stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
//        stv_aktualny.setDatas(createTopTitlesAktualny(), createLeftTitleAktualny(), resultsAktualny);


        seekBar = (SeekBar) myView.findViewById(R.id.seekBar1);
        seekBar.setProgress(15);
        seekBar.incrementProgressBy(3);
        seekBar.setMax(90);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a = progress + 15;

                prog = progress;

                ArrayList<String> topTitlesLoty = createTopTitlesLoty();
                ArrayList<String> leftTitleLoty = createLeftTitleLoty();


                stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
                 ll = (LinearLayout) myView.findViewById(R.id.layout_loty);
//                ll.setBackgroundColor(getResources().getColor(R.color.transparent));
//                ll.invalidate();

                wysokosc = wysokosc + (progress * 96);

                ll.getLayoutParams().height = wysokosc;
                ll.requestLayout();


            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        LinearLayout l = (LinearLayout) myView.findViewById(R.id.nal_akt);
        l.requestLayout();
    }

    @Override
    public void onClick(View v) {

        LinearLayout linearLayout = (LinearLayout) myView.findViewById(R.id.zakresDat_layout);
        linearLayout.setVisibility(View.VISIBLE);

        LinearLayout ll = (LinearLayout) myView.findViewById(R.id.wykonaneLoty);

        int wys = wysokosc + (prog * 96) ;

        ll.getLayoutParams().height = wys ;
        ll.requestLayout();
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


    private ArrayList<String> createTopTitlesLoty() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitlesLoty) {
            results.add(string);
        }
        return results;
    }

    private ArrayList<String> createTopTitlesAktualny() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitlesAktualny) {
            results.add(string);
        }
        return results;
    }


    private ArrayList<String> createLeftTitle() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitles) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

    private ArrayList<String> createLeftTitleSym() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitlesSym) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

    private ArrayList<String> createLeftTitleAktualny() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitlesAktualny) {
            if (string != null && !string.isEmpty())
            {
                results.add(string);}
        }
        return results;
    }

        private ArrayList<String> createTopTitlesRoczny() {
            ArrayList<String> results = new ArrayList<>();
            for (String string : topTitlesRoczny) {
                results.add(string);
            }
            return results;
        }


    private ArrayList<String> createLeftTitleLoty() {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 14; i < a; i++) {
            results.add(i+".01.17");
        }
        return results;
    }


    private ArrayList<ArrayList<String>> createContent1(String[] row1, String[] row2, String[] row3, String[] row4 ) {


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

        if (row4 != null ) {
            ArrayList<String> strings4 = new ArrayList<>();
            for (String string : row4) {
                strings4.add(string);
            }
            results.add(strings4);
        }

        return results;
    }

//    private ArrayList<ArrayList<String>> createContent1(String[] row1, String[] row2, String[] row3 ) {
//
//
//        ArrayList<ArrayList<String>> results = new ArrayList<>();
//        ArrayList<String> strings1 = new ArrayList<>();
//        for (String string : row1) {
//            strings1.add(string);
//        }
//        results.add(strings1);
//
//        ArrayList<String> strings2 = new ArrayList<>();
//        for (String string : row2) {
//            strings2.add(string);
//        }
//        results.add(strings2);
//
//        ArrayList<String> strings3 = new ArrayList<>();
//        for (String string : row3) {
//            strings3.add(string);
//        }
//        results.add(strings3);
//
//
//        return results;
//    }


    public void addItemsOnSpinner() {
        ArrayList<String> years= new ArrayList<>(Arrays.asList("2017","2016","2015","2014","2013","2012"));

        spinRocznySp = (Spinner) myView.findViewById(R.id.spinnerRokSp);
        spinRocznySym = (Spinner) myView.findViewById(R.id.spinnerRokSym);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRocznySp.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRocznySym.setAdapter(spinnerArrayAdapter);

    }






}





