package com.example.mvvmboradtest.repositories;

import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardShowDetailDeleteRepository {
    private ApiService apiService;

    public void BoardShowDetailDeleteRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void BoardDetailDelete(int board_id, BoardDeleteInterface deleteInterface) {
        apiService.getBoardOneDataDelete(board_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    deleteInterface.BoardDeleteSuccessResponse(response.body().string());
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
