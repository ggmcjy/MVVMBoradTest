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
import com.example.mvvmboradtest.databinding.ActivityBoardBinding;
import com.example.mvvmboradtest.interfaces.BoardCreateInterface;
import com.example.mvvmboradtest.viewmodels.BoardCreateViewModel;

import org.json.JSONObject;

public class BoardActivity extends AppCompatActivity {
    private ActivityBoardBinding binding;
    private BoardCreateViewModel viewModel;
    private String title = "";
    private String content = "";
    String title_trim = "";
    String content_trim  = "";
    private int board_user_id = 0;
    int _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board);
        _id = getIntent().getIntExtra("user_id", 0);
        binding.btnBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                binding.etTitle.setText("");
                binding.etContent.setText("");
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardCreateInit();
                binding.etTitle.setText("");
                binding.etContent.setText("");
            }
        });

        binding.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectAllActivity.class);
                intent.putExtra("user_id", _id);
                startActivity(intent);
            }
        });
    }


    private void BoardCreateInit() {
        BoardTextExists();
        Log.e("length" , title_trim.length() + "    " + content_trim.length());
        if (title_trim.length() <= 4) {
            Toast.makeText(getApplicationContext(), "제목 글자수 5글자 이상으로 써주시기 바랍니다.",Toast.LENGTH_SHORT).show();
        } else if (content_trim.length() <= 9){
            Toast.makeText(getApplicationContext(), "내 글자수 10글자 이상으로 써주시기 바랍니다.",Toast.LENGTH_SHORT).show();
        } else {
            viewModel = new ViewModelProvider(this).get(BoardCreateViewModel.class);
            viewModel.getBoardCreateResponse(board_user_id,title,content,createInterface);
        }
    }


    private void BoardTextExists() {
        Log.e("BoardActivity", _id + "");
        board_user_id = _id;
        title = binding.etTitle.getText().toString();
        content = binding.etContent.getText().toString();
        title_trim = title.replaceAll("\\p{Z}", "");
        content_trim  = content.replaceAll("\\p{Z}", "");
        Log.e("trim", title_trim + content_trim);
    }



    private BoardCreateInterface createInterface = new BoardCreateInterface() {
        @Override
        public void BoardCreateSuccessResponse(String board_create_str) {
            try {
                JSONObject jsonObject = new JSONObject(board_create_str);
                Boolean success = jsonObject.getBoolean("success");
                String message = jsonObject.getString("message");
                Log.e("message" , message);
                if (success) {
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),message + "",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}