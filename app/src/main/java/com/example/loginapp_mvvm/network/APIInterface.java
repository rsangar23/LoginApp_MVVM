package com.example.loginapp_mvvm.network;

import com.example.loginapp_mvvm.model.LoginResponse;
import com.example.loginapp_mvvm.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);

}
