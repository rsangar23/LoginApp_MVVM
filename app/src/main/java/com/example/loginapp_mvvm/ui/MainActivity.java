package com.example.loginapp_mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginapp_mvvm.R;
import com.example.loginapp_mvvm.databinding.ActivityMainBinding;
import com.example.loginapp_mvvm.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

//https://github.com/rsangar23/LoginApp_MVVM - git

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setMainActivity(this);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

//        init();
        initObserver();
    }

//    private void init(){
//        loginViewModel.init();
//    }

    private void initObserver(){
        loginViewModel.getLoginResponseLiveData().observe(this, loginResponse -> {
            if(loginResponse != null){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
                binding.progressbar.setVisibility(View.GONE);
            }else {
                binding.progressbar.setVisibility(View.GONE);
                binding.editTextTextEmailAddress.setError("Please enter valid email");
                binding.editTextTextPassword.setError("Please enter valid password");
            }
        });
    }

    public void login(){
        binding.progressbar.setVisibility(View.VISIBLE);
        loginViewModel.login();
    }
}