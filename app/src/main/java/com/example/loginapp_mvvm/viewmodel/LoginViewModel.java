package com.example.loginapp_mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginapp_mvvm.model.LoginResponse;
import com.example.loginapp_mvvm.model.UserModel;
import com.example.loginapp_mvvm.repository.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class LoginViewModel extends AndroidViewModel {

    UserRepository userRepository;

    @Inject
    public LoginViewModel(@NonNull Application application, UserRepository userRepository) {
        super(application);
        this.userRepository = userRepository;
        loginResponseLiveData = userRepository.getLoginResponseMutableLiveData();

    }

    private LiveData<LoginResponse> loginResponseLiveData;
    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");

//    public LoginViewModel(@NonNull Application application) {
//        super(application);
//    }

//    public void init(){
//        userRepository = new UserRepository();
//        loginResponseLiveData = userRepository.getLoginResponseMutableLiveData();
//    }

    public void login(){
//        UserModel userModel = new UserModel();
//        userModel.setEmail(email.getValue());
//        userModel.setPassword(password.getValue());

        userRepository.login(email.getValue(), password.getValue());
    }

    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }
}
