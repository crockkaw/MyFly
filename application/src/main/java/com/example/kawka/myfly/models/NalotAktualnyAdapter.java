package com.example.kawka.myfly.models;

import com.example.kawka.myfly.network.ApiService;
import com.example.kawka.myfly.network.ServiceGenerator;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by kawka on 3/8/2017.
 */
public class NalotAktualnyAdapter {

    private ArrayList<ArrayList<String>> resultsNalAkt;
    private String[] leftTitlesNalAkt = new String[10];
    String pattern, request;

    public NalotAktualnyAdapter(String userId) {

        pattern = "RowFinder;id_num=";
        request = pattern + userId;

        ApiService client = ServiceGenerator
                .createService(ApiService.class,"client","Turawa2016");

        Call<NalotAktualny> call = client.getNalaktu(request);
        call.enqueue(new Callback<NalotAktualny>() {
            @Override
            public void onResponse(Call<NalotAktualny> call,
                                   Response<NalotAktualny> response) {
                NalotAktualny nalot = response.body();
                int b = nalot.getItems().size();
                for (int k = 0; k < b; k++) {
                    leftTitlesNalAkt[k] = nalot.getItems()
                            .get(k).getNazwa();
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
            public void onFailure(Call<NalotAktualny> call,
                                  Throwable t) {
                System.out.println("Błąd połączenia"); }
        });
    }
    public String[] getMyLeftTitlesAktualny() {
        return leftTitlesNalAkt;}
    public ArrayList<ArrayList<String>> getMyResultsAktualny() {
        return resultsNalAkt;}
}
