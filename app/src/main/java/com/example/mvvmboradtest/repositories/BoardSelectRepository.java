package com.example.mvvmboradtest.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmboradtest.interfaces.BoardSelectInterface;
import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;
import com.example.mvvmboradtest.response.BoardShowResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardSelectRepository {
    private ApiService apiService;
    private BoardSelectInterface selectInterface;
    public BoardSelectRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<BoardShowResponse> getBoardShow(int user) {
        MutableLiveData<BoardShowResponse> data = new MutableLiveData<>();
        apiService.getBoardData(user).enqueue(new Callback<BoardShowResponse>() {
           @Override
           public void onResponse(Call<BoardShowResponse> call, Response<BoardShowResponse> response) {
               data.setValue(response.body());
           }

           @Override
           public void onFailure(Call<BoardShowResponse> call, Throwable t) {
               data.setValue(null);
           }
       });
        return data;
    }
}
