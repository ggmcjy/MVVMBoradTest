package com.example.mvvmboradtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.databinding.ActivityMainBinding;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });
    }


    private void CreateUser () {
        String name = binding.etName.getText().toString();
        int birth = Integer.parseInt(binding.etBirth.getText().toString());
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.CreateUser(name, birth).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("lhs", response.body().string());
                }catch (Exception e) {
                    Log.e("lhs", e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}