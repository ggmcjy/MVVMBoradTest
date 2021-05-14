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
import com.example.mvvmboradtest.databinding.ActivityMainBinding;
import com.example.mvvmboradtest.dialog.MessageDialog;
import com.example.mvvmboradtest.dialog.MessageInterface;
import com.example.mvvmboradtest.interfaces.LoginInterface;
import com.example.mvvmboradtest.network.ApiClient;
import com.example.mvvmboradtest.network.ApiService;
import com.example.mvvmboradtest.viewmodels.LoginViewModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ApiService apiService;
    private LoginViewModel viewModel;
    private String Login_id = "" ;
    private String Login_password = "";
    private MessageDialog messageDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginInit();
            }
        });
        binding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etId.setText("");
                binding.etPassword.setText("");
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        ShowMessageDialog();
    }

    private void ShowMessageDialog() {
        messageDialog = new MessageDialog();
        messageDialog.ShowMessageDialog(this, "EXIT" , "앱을 종료하시겠습니까?" , messageInterface,true );
    }

    private MessageInterface messageInterface = new MessageInterface() {
        @Override
        public void DeleteYes() {
            finish();
        }

        @Override
        public void DeleteNo() {

        }

        @Override
        public void ErrorConfirm() {

        }
    };




    private void LoginInit() {
        LoginGetText();
        if (Login_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Id 를 입력해주세요.!" ,Toast.LENGTH_SHORT).show();
        } else if (Login_password.equals("")) {
            Toast.makeText(getApplicationContext(), "Pw 를 입력해주세요.!" ,Toast.LENGTH_SHORT).show();
        } else {
            viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            viewModel.LoginAction(Login_id, Login_password, loginInterface);
        }
    }

    private void LoginGetText() {
        Login_id = binding.etId.getText().toString();
        Login_password = binding.etPassword.getText().toString();
    }

    private LoginInterface loginInterface = new LoginInterface() {
        @Override
        public void LoginSuccessResponse(String l_str) {
            Log.e("ssss", l_str);
            try {
                JSONObject jsonObject = new JSONObject(l_str);
                Boolean success = jsonObject.getBoolean("success");
                String message = jsonObject.getString("message");
                if (success) {
                    Toast.makeText(getApplicationContext(), ""+message , Toast.LENGTH_SHORT).show();
                    int user_session_id = jsonObject.getInt("userdata");
                    Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                    intent.putExtra("user_id", user_session_id);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void LoginFailureResponse() {

        }
    };
}