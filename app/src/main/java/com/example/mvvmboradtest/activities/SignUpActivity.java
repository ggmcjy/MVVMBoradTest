package com.example.mvvmboradtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.databinding.ActivitySignUpBinding;
import com.example.mvvmboradtest.interfaces.SignUpInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;
import com.example.mvvmboradtest.viewmodels.SignUpViewModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding activitySignUpBinding;
    private SignUpViewModel viewModel;
    private ApiService apiService;
    private String sign_id = "";
    private String sign_password = "";
    private String sign_name = "";
    private String sign_birth = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        activitySignUpBinding.btnSignback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        activitySignUpBinding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInitialization();
            }
        });

    }


    private void UserGetText() {
        sign_id = activitySignUpBinding.etSignId.getText().toString();
        sign_password = activitySignUpBinding.etSignpassword.getText().toString();
        sign_name = activitySignUpBinding.etSignName.getText().toString();
        sign_birth = activitySignUpBinding.etSignbirth.getText().toString();
        Log.e("UserGetText", sign_id+sign_password+sign_name+sign_birth);

    }

    private void doInitialization() {
        UserGetText();
        if (sign_id .equals("")) {
            Toast.makeText(getApplicationContext(),"아이디를 입력 해주세요", Toast.LENGTH_SHORT).show();
        } else if (sign_password.equals("")) {
            Toast.makeText(getApplicationContext(),"비밀번호를 입력 해주세요", Toast.LENGTH_SHORT).show();
        } else if (sign_name.equals("")) {
            Toast.makeText(getApplicationContext(),"이름을 입력 해주세요", Toast.LENGTH_SHORT).show();
        } else if (sign_birth.equals("")) {
            Toast.makeText(getApplicationContext(), "주민번호 앞자리를 입력 해주세요", Toast.LENGTH_SHORT).show();
        } else {
            viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
            viewModel.getCreateUser(sign_id,sign_password,sign_name,sign_birth,signUpInterface);
        }

    }

    private SignUpInterface signUpInterface = new SignUpInterface() {
        @Override
        public void mResponse(String r_str) {
            try {
                JSONObject jsonObject = new JSONObject(r_str);
                String message = jsonObject.getString("message");
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    activitySignUpBinding.etSignId.setText("");
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void mFailure(String f_str) {

        }
    };






}