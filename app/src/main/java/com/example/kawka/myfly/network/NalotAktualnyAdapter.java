package com.example.kawka.myfly.network;

import android.widget.Toast;

import com.example.kawka.myfly.MainActivity;
import com.example.kawka.myfly.models.NalotAktualny;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

//    String API_BASE_URL = "http://kkawka.pl:7001/rest/api/v0/";


    private ArrayList<ArrayList<String>> resultsNalAkt;
    private String[] leftTitlesNalAkt = new String[10];

    public NalotAktualnyAdapter() {

//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        // add logging as last interceptor
//        httpClient.addInterceptor(logging);  // <-- this is the important line!
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(API_BASE_URL)
//                .client(httpClient.build());
//        Retrofit retrofit = builder.build();


        ApiService client = ServiceGenerator.createService(ApiService.class);

        Call<NalotAktualny> call = client.getItems();


        call.enqueue(new Callback<NalotAktualny>() {
            @Override
            public void onResponse(Call<NalotAktualny> call, Response<NalotAktualny> response) {
                NalotAktualny nalot = response.body();

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

            }

            @Override
            public void onFailure(Call<NalotAktualny> call, Throwable t) {
                System.out.println("Błąd połączenia");            }
        });
    }

//        Observable<NalotAktualny> nal = apiService.getItems("40");
//
//        nal.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(nalot -> {
//                    int b = nalot.getItems().size();
//                    for (int k = 0; k < b; k++) {
//                        leftTitlesNalAkt[k] = nalot.getItems().get(k).getNazwa();
//                    }
//
//                    resultsNalAkt = new ArrayList<>();
//                    for (int l = 0; l < b; l++) {
//                        ArrayList<String> strings = new ArrayList<>();
//                        strings.add(nalot.getItems().get(l).getOgolny());
//                        strings.add(nalot.getItems().get(l).getDv());
//                        strings.add(nalot.getItems().get(l).getDi());
//                        strings.add(nalot.getItems().get(l).getNv());
//                        strings.add(nalot.getItems().get(l).getNi());
//
//                        resultsNalAkt.add(strings);
//                    }
//
//
//                },error -> System.out.println(error.toString()));



    public String[] getMyLeftTitlesAktualny() {
        return leftTitlesNalAkt;
    }

    public ArrayList<ArrayList<String>> getMyResultsAktualny() {
        return resultsNalAkt;
    }
}
