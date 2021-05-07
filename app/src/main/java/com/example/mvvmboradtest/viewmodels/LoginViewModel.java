package com.example.mvvmboradtest.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.LoginInterface;
import com.example.mvvmboradtest.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public void LoginAction(String loging_id, String login_password, LoginInterface loginInterface) {
        loginRepository.LoginAction(loging_id, login_password, loginInterface);
    }
}
