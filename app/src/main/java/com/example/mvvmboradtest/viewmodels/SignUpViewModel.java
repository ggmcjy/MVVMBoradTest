package com.example.mvvmboradtest.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.SignUpInterface;
import com.example.mvvmboradtest.repositories.SignUpRepository;

public class SignUpViewModel extends ViewModel {
    private SignUpRepository signUpRepository;
    public SignUpInterface signUpInterface;

    public SignUpViewModel() {
        signUpRepository = new SignUpRepository();
    }

    public void getCreateUser(String user_id, String user_password, String user_name, String user_birth, SignUpInterface _listen) {
        signUpRepository.getCreateUser(user_id, user_password,user_name,user_birth, _listen);
    }
}
