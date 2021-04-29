package com.example.loginapp_mvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.loginapp_mvvm.model.LoginResponse;
import com.example.loginapp_mvvm.model.UserModel;
import com.example.loginapp_mvvm.network.APIClient;
import com.example.loginapp_mvvm.network.APIInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    private APIInterface apiInterface;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;

    public UserRepository() {
        loginResponseMutableLiveData = new MutableLiveData<>();

        apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    public void login(UserModel userModel){

        apiInterface.login(userModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<LoginResponse> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }
}
