package com.example.shall_we_merge.api;


import com.example.shall_we_merge.login.signup.TestDataClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface ShallWeMergeAPI {

    @POST("/signup")
    Call<AccountDataClass> signUp(@Body AccountDataClass accountDataClass);

    @POST("/signin")
    Call<AccountDataClass> login(@Body AccountDataClass accountDataClass);

    @POST("/checkId")
    Call<IdDataClass> checkId(@Body IdDataClass idDataClass);

    @POST("/kakaologin")
    Call<IdDataClass> kakaoLogin(@Body IdDataClass idDataClass);

    @POST("/getschedules")
    Call<List<ScheduleDataClass>> getSchedules(@Body ScheduleDataClass scheduleDataClass);



    @POST("/addPlace")
    Call<TestDataClass> addPlace(@Body TestDataClass testDataClass);




}