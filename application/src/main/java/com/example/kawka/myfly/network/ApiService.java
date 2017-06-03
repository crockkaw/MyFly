package com.example.kawka.myfly.network;

import com.example.kawka.myfly.models.NalotAktualny;
import com.example.kawka.myfly.models.ZibConfirm;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kawka on 3/6/2017.
 */

public interface ApiService {


    @POST("apka/rest/v0/zib")
    Call<ZibConfirm> createZibCon(@Header("Content-Type") String content_type, @Body ZibConfirm task);

    @GET("rest/api/v0/nal_cal")
    Call<NalotAktualny> getNalaktu(@Query("finder") String finder);

//    @GET("rest/api/v0/nal_cal?finder=RowFinder;id_num=40")
//    Call<NalotAktualny> getNalaktu();


}
