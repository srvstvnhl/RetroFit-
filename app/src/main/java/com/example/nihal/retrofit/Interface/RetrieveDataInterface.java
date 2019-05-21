package com.example.nihal.retrofit.Interface;

import com.example.nihal.retrofit.Pojo.JSONResponse;
import com.example.nihal.retrofit.Pojo.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrieveDataInterface {

    @GET("/ub-android-app/v1.9/services.php?act=gettournaments")
    Call<JSONResponse> getJson();

    @GET("ub-android-app/v1.9/services.php/ub-android-app/v1.9/services.php?act=tournament_details")
    Call<MyResponse> getTournamentDetails(@Query("tournament_id") String id);

}
