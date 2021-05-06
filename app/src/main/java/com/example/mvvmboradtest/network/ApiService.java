package com.example.mvvmboradtest.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @POST(STR_NETWORK.SERVER_URL)
    @FormUrlEncoded
    Call<ResponseBody> CreateUser (@Field("name") String name,
                                   @Field("birth") int birth);

}
