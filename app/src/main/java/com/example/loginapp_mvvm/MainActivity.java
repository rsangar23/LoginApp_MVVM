package com.example.loginapp_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginapp_mvvm.databinding.ActivityMainBinding;
import com.example.loginapp_mvvm.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setMainActivity(this);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        init();
        initObserver();
    }

    private void init(){
        loginViewModel.init();
    }

    private void initObserver(){
        loginViewModel.getLoginResponseLiveData().observe(this, loginResponse -> {
            if(loginResponse.getUser().getAccessToken() != null){
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                binding.progressbar.setVisibility(View.GONE);
            }
        });
    }

    public void login(){
        binding.progressbar.setVisibility(View.VISIBLE);
        loginViewModel.login();
    }
}