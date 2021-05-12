package com.example.mvvmboradtest.repositories;

import com.example.mvvmboradtest.interfaces.BoardCreateInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardCreateRepository {
    private ApiService apiService;


    public BoardCreateRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void getBoardCreateResponse(int board_user_id, String board_title, String board_content, BoardCreateInterface boardCreateInterface) {
        apiService.BoardCreate(board_user_id, board_title, board_content).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    boardCreateInterface.BoardCreateSuccessResponse(response.body().string());
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
