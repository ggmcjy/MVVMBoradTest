package com.example.mvvmboradtest.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.databinding.ActivityBoardDetailBinding;
import com.example.mvvmboradtest.dialog.MessageDialog;
import com.example.mvvmboradtest.dialog.MessageInterface;
import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.viewmodels.BoardDeleteViewModel;

import org.json.JSONObject;

public class BoardDetailActivity extends AppCompatActivity {
    private ActivityBoardDetailBinding binding;
    private BoardModels boardModels;
    private BoardDeleteViewModel viewModel;
    private MessageDialog messageDialog;
    private int board_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_detail);
        boardModels = (BoardModels) getIntent().getSerializableExtra("board");
        LoadBoardDetail();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardIntent();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMessageShow();
            }
        });

    }


    private void LoadBoardDetail() {
        Log.e("type" , boardModels.getCreate_date().getClass().getName());
        String create_date = boardModels.getCreate_date();
        String rep_create_date = create_date.replace("T" ," ").substring(0,19);
        Log.e("String" , rep_create_date);
        binding.setBoardUserId(boardModels.getLogin_id());
        binding.setBoardTitle(boardModels.getTitle());
        binding.setBoardContent(boardModels.getContent());
        binding.setCreatedTime(rep_create_date);
    }

    private void BoardDeleteInit() {
        board_id = boardModels.getBoard_id();
        Log.e("board_id" , board_id + "");
        Log.e("board_id2" , boardModels.getUser_id() +"");
        viewModel = new ViewModelProvider(this).get(BoardDeleteViewModel.class);
        viewModel.BoardDetailDelete(board_id, boardDeleteInterface);
    }

    private BoardDeleteInterface boardDeleteInterface = new BoardDeleteInterface() {
        @Override
        public void BoardDeleteSuccessResponse(String board_delete_str) {
            try {
                JSONObject jsonObject = new JSONObject(board_delete_str);
                String message = jsonObject.getString("message");
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    BoardIntent();
                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Log.e("delete error", e.toString());
            }

        }
    };

    private void BoardIntent () {
        Intent intent = new Intent(getApplicationContext(), SelectAllActivity.class);
        intent.putExtra("user_id", boardModels.getUser_id());
        startActivity(intent);
        finish();
    }

    private void DeleteMessageShow() {
        messageDialog = new MessageDialog();
        messageDialog.ShowMessageDialog(this, "DELETE", "해당 게시판을 삭제하시겠습니까?", messageInterface, true);
    }




    private MessageInterface messageInterface = new MessageInterface() {
        @Override
        public void DeleteYes() {
            BoardDeleteInit();
        }

        @Override
        public void DeleteNo() {

        }

        @Override
        public void ErrorConfirm() {

        }
    };

    @Override
    public void onBackPressed() {
        BoardIntent();
    }
}