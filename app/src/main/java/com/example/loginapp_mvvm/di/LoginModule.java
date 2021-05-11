package com.example.loginapp_mvvm.di;

import com.example.loginapp_mvvm.network.APIClient;
import com.example.loginapp_mvvm.network.APIInterface;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LoginModule {

    @Provides
    public APIInterface provideApi(){
        return APIClient.getClient().create(APIInterface.class);
    }
}
