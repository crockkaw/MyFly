package com.example.kawka.myfly.network;

import com.example.kawka.myfly.models.NalotAktualny;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kawka on 3/6/2017.
 */

public interface ApiService {

    @GET("nal_akt")
    Observable<NalotAktualny> getItems();
}
