package com.example.mvvmboradtest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.adapters.BoardsAdapter;
import com.example.mvvmboradtest.databinding.ActivitySelectAllBinding;
import com.example.mvvmboradtest.interfaces.BoardSelectInterface;
import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.response.BoardShowResponse;
import com.example.mvvmboradtest.utils.BoardRecyclerViewDecoration;
import com.example.mvvmboradtest.viewmodels.BoardSelectViewModel;

import java.util.ArrayList;
import java.util.List;

public class SelectAllActivity extends AppCompatActivity implements BoardSelectInterface {
    private ActivitySelectAllBinding binding;
    private List<BoardModels> boardModels = new ArrayList<>();
    private BoardsAdapter boardsAdapter;
    private BoardSelectViewModel viewModel;
    private Integer total = 0;
    private int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_all);
        user_id = getIntent().getIntExtra("user_id", 0);
        Log.e("user_id", user_id+"");
        doInitialization();
    }





    private void doInitialization() {
        // 아이템의 크기가 변하는 경우는 없을 것이고, 그렇다면 setHasFixedSize를 true로 설정함으로써 변경되지 않는다는 것을 명시하는게 좋다.
        // 따라서 레이아웃을 다시 그리는 비용이 많이 드는 작업을 피하도록 하여 성능 하락을 방지할 수 있다고 생각한다.
        binding.boardShowRescycleView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(BoardSelectViewModel.class);
        boardsAdapter = new BoardsAdapter(boardModels, this);
        binding.boardShowRescycleView.setAdapter(boardsAdapter);
        binding.boardShowRescycleView.addItemDecoration(new BoardRecyclerViewDecoration(30));
        getBoardView();
    }



    private void getBoardView () {
        viewModel.getBoardShow(user_id).observe(this, new Observer<BoardShowResponse>() {
            @Override
            public void onChanged(BoardShowResponse boardShowResponse) {
                int oldCount = boardModels.size();
                boardModels.addAll(boardShowResponse.getBoardModels());
                boardsAdapter.notifyItemRangeInserted(oldCount, boardModels.size());
            }
        });
    }


    @Override
    public void onBoardClicked(BoardModels boardModels) {
        Intent intent = new Intent(getApplicationContext(), BoardDetailActivity.class);
        intent.putExtra("board", boardModels);
        startActivity(intent);


    }
}