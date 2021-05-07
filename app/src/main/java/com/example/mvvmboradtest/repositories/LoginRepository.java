package com.example.mvvmboradtest.repositories;

import com.example.mvvmboradtest.interfaces.LoginInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private ApiService apiService;


    public LoginRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }


    public void LoginAction (String loging_id, String login_password, LoginInterface loginInterface) {
        apiService.LoginUser(loging_id, login_password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    loginInterface.LoginSuccessResponse(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
