package com.example.shall_we_merge;


import com.example.shall_we_merge.login.LoginDataClass;
import com.example.shall_we_merge.login.signup.TestDataClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShallWeMergeAPI {

    @GET("/signup")
    Call<LoginDataClass> getAccount();

    //@FormUrlEncoded

    @POST("/signin")
    Call<LoginDataClass> postAccount(@Body LoginDataClass loginDataClass);

    @POST("/addPlace")
    Call<TestDataClass> addPlace(@Body TestDataClass testDataClass);


}