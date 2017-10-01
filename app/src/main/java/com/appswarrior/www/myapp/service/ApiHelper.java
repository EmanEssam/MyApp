package com.appswarrior.www.myapp.service;

import com.appswarrior.www.myapp.model.CountryResponse;
import com.appswarrior.www.myapp.model.LoginResponse;
import com.appswarrior.www.myapp.register.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public interface ApiHelper {
    @FormUrlEncoded
    @POST("LogIn?")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("register?")
    Call<RegisterResponse> register(@Field("name") String name, @Field("username") String username, @Field("mail") String mail, @Field("password") String password, @Field("mobile") String mobile, @Field("gender") int gender);

    @GET("all")
    Call<List<CountryResponse>> getCountryList();
}
