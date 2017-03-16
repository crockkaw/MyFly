package com.example.kawka.myfly.network;

import com.example.kawka.myfly.models.NalotAktualny;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kawka on 3/8/2017.
 */

public class NalotAktualnyAdapter {

    private ArrayList<ArrayList<String>> resultsNalAkt;
    private String[] leftTitlesNalAkt = new String[10];

    public NalotAktualnyAdapter() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://kkawka.pl:7001/rest/api/v0/")
                .build();


        ApiService apiService = retrofit.create(ApiService.class);
        Observable<NalotAktualny> nal = apiService.getItems();

        nal.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nalot -> {
                    int b = nalot.getItems().size();
                    for (int k = 0; k < b; k++) {
                        leftTitlesNalAkt[k] = nalot.getItems().get(k).getNazwa();
                    }

                    resultsNalAkt = new ArrayList<>();
                    for (int l = 0; l < b; l++) {
                        ArrayList<String> strings = new ArrayList<>();
                        strings.add(nalot.getItems().get(l).getOgolny());
                        strings.add(nalot.getItems().get(l).getDv());
                        strings.add(nalot.getItems().get(l).getDi());
                        strings.add(nalot.getItems().get(l).getNv());
                        strings.add(nalot.getItems().get(l).getNi());

                        resultsNalAkt.add(strings);
                    }


                },error -> System.out.println(error.toString()));


    }

    public String[] getMyLeftTitlesAktualny() {
        return leftTitlesNalAkt;
    }

    public ArrayList<ArrayList<String>> getMyResultsAktualny() {
        return resultsNalAkt;
    }
}
