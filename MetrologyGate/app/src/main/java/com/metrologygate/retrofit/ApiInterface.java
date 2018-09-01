package com.metrologygate.retrofit;

import com.metrologygate.models.InstrumentDashboard.InstrumentDashboard;
import com.metrologygate.models.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Karan on 07-04-2018.
 *///

public interface ApiInterface {
    @GET("Login")
    @Headers("Content-Type: application/json")
    Call<Login> login(@Query("userName") String username, @Query("password") String password);

    @GET("GetUserInstruments")
    @Headers("Content-Type: application/json")
    Call<ArrayList<String>> getInstrumentsList(@Query("userName") String username, @Query("password") String password);

    @GET("GetInstrument")
    @Headers("Content-Type: application/json")
    Call<InstrumentDashboard> getInstrumentsData(@Query("instrumentName") String instrumentName);


}
