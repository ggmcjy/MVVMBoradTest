package com.example.mvvmboradtest.repositories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mvvmboradtest.activities.MainActivity;
import com.example.mvvmboradtest.interfaces.SignUpInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {

    private ApiService apiService;
    private SignUpInterface signUpInterface;
    String Sign_data = "";

    public SignUpRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void getCreateUser(String user_id, String user_password, String user_name, String user_birth, SignUpInterface signUpInterface) {
        apiService.CreateUser(user_id,user_password,user_name,user_birth).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    signUpInterface.mResponse(response.body().string());
                } catch (Exception e) {
                    Log.e("SignUp_error" ,  e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}
