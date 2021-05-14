package com.example.mvvmboradtest.network;

import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.response.BoardShowResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @POST(STR_NETWORK.SERVER_URL)
    @FormUrlEncoded
    Call<ResponseBody> CreateUser (@Field("user_id") String user_id,
                                   @Field("user_password") String user_password,
                                   @Field("user_name") String user_name,
                                   @Field("user_birth") String user_birth);

    @POST(STR_NETWORK.LOGIN_URL)
    @FormUrlEncoded
    Call<ResponseBody> LoginUser (@Field("login_id") String login_id,
                                  @Field("login_password") String login_password);

    @POST(STR_NETWORK.BOARD_CREATE_URL)
    @FormUrlEncoded
    Call<ResponseBody> BoardCreate (@Field("board_user_id") int board_user_id,
                                    @Field("board_title") String board_title,
                                    @Field("board_content") String board_content);


    @POST(STR_NETWORK.BOARD_SELECT)
    Call<BoardShowResponse> getBoardData(@Path("user-id") int user_id);

    @POST(STR_NETWORK.BOARD_SELECT_DELETE)
    @FormUrlEncoded
    Call<ResponseBody> getBoardOneDataDelete (@Field("board_id") int board_id);
}
