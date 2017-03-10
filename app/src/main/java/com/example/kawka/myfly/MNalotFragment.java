package com.example.kawka.myfly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.example.kawka.myfly.models.Item;
import com.example.kawka.myfly.models.Nalot;
import com.example.kawka.myfly.network.ApiService;
import com.loopeer.android.librarys.scrolltable.ScrollTableView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MNalotFragment extends Fragment  implements View.OnClickListener {

    View myView;

    public int a = 22;
    SeekBar seekBar;
    int wysokosc = 264;

    List nalotAktualny;
    Item item;
    LinearLayout ll;

    int be;


    ArrayList<ArrayList<String>> resultsAktualny;


    private static final String[] topTitlesLoty = new String[]{"Typ sp", "Funkcja/pozycja", "Cw. wyk. w locie", "Czas lotu", "Godzina startu", "Godzina lądowania"};
    private static final String[] topTitlesRoczny = new String[]{"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Listopad", "Grudzień"};
    private static final String[] topTitles = new String[]{"Nalot całkowity", "Nalot VFR dzień", "Nalot IFR dzień", "Nalot VDR noc", "Nalot IFR noc"};

//    private static final String[] leftTitleLoty = new String[]{"Nalot całkowity", "Nalot VFR dzień", "Nalot IFR dzień", "Nalot VDR noc", "Nalot IFR noc"};

    private String[] leftTitlesAktualny = new String[10];
    private static final String[] contentAktualny = new String[10];


    private ScrollTableView stv_loty, stv_aktualny, stv_roczny, stv_roczny_sym;
    Button dataButton1;
    int prog = 15;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mnalot, container, false);

        MainActivity activity = (MainActivity) getActivity();
        leftTitlesAktualny = activity.getMyLeftTitlesAktualny();
        resultsAktualny = activity.getMyResultsAktualny();

        System.out.println("Odebrano dane w Fragmencie ");
        System.out.println(resultsAktualny.get(0).get(0));

        initialization();

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
        ArrayList<String> topTitles = createTopTitles();
        ArrayList<String> topTitlesRoczny = createTopTitlesRoczny();
        ArrayList<String> leftTitleLoty = createLeftTitleLoty();



        stv_loty.setDatas(createTopTitlesLoty(), createLeftTitleLoty(), createContent(leftTitleLoty.size(), topTitlesLoty.size()));
        stv_roczny.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
        stv_roczny_sym.setDatas(createTopTitlesRoczny(), createLeftTitle(), createContent(leftTitle.size(), topTitlesRoczny.size()));
        stv_aktualny.setDatas(createTopTitles(), createLeftTitle(), resultsAktualny);



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

    private ArrayList<String> createTopTitles() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : topTitles) {
            results.add(string);
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


    private ArrayList<String> createLeftTitle() {
        ArrayList<String> results = new ArrayList<>();
        for (String string : leftTitlesAktualny) {
            if (string != null && !string.isEmpty())
            {
            results.add(string);}
        }
        return results;
    }



}





