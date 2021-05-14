package com.example.mvvmboradtest.repositories;

import android.util.Log;

import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardShowDetailDeleteRepository {
    private ApiService apiService;

    public BoardShowDetailDeleteRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void BoardDetailDelete(int board_id, BoardDeleteInterface deleteInterface) {
        apiService.getBoardOneDataDelete(board_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("BoardDetail", "11111111111");
                    deleteInterface.BoardDeleteSuccessResponse(response.body().string());
                } catch (Exception e) {
                    Log.e("delete error", e.toString());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
