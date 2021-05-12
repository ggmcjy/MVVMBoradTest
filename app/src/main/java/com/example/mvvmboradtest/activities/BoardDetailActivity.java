package com.example.mvvmboradtest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.databinding.ActivityBoardDetailBinding;
import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.viewmodels.BoardDeleteViewModel;

public class BoardDetailActivity extends AppCompatActivity {
    private ActivityBoardDetailBinding binding;
    private BoardModels boardModels;
    private BoardDeleteViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_detail);
        boardModels = (BoardModels) getIntent().getSerializableExtra("board");
        LoadBoardDetail();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardDeleteInit();
            }
        });

    }


    private void BoardDeleteInit() {
        viewModel = new ViewModelProvider(this).get(BoardDeleteViewModel.class);
        viewModel.BoardDetailDelete(boardModels.getBoard_id(), boardDeleteInterface);
    }

    private BoardDeleteInterface boardDeleteInterface = new BoardDeleteInterface() {
        @Override
        public void BoardDeleteSuccessResponse(String board_delete_str) {

        }
    };


    private void LoadBoardDetail() {
        binding.setBoardUserId(boardModels.getLogin_id());
        binding.setBoardTitle(boardModels.getTitle());
        binding.setBoardContent(boardModels.getContent());
        binding.setCreatedTime(boardModels.getCreate_date());
    }
}