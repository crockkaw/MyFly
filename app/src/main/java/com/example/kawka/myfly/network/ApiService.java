package com.example.kawka.myfly.network;

import com.example.kawka.myfly.models.NalotAktualny;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kawka on 3/6/2017.
 */

public interface ApiService {


    @GET("nal_cal?finder=RowFinder;id_num=40")
    Call<NalotAktualny> getItems();
}
