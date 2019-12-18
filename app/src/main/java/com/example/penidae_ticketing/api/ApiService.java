package com.example.penidae_ticketing.api;

import com.example.penidae_ticketing.model.Auth;
import com.example.penidae_ticketing.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("api.php?apicall=login")
    Call<User>login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?apicall=signup")
    Call<User>register(@Field("name") String name,
                       @Field("username") String username,
                       @Field("email") String email,
                       @Field("password") String password,
                       @Field("phone") String phone);



}
