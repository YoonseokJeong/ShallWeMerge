package com.example.project_2nd_week.Login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginAPI {

    @GET("/signup")
    Call<LoginDataClass> getAccount();

    @FormUrlEncoded
    @POST("/signup")
    Call<LoginDataClass> postAccount(@Field("id") String id, @Field("password") String password);

}
