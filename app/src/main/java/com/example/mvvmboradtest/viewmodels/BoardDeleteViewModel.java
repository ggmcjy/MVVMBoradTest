package com.example.mvvmboradtest.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.repositories.BoardShowDetailDeleteRepository;

public class BoardDeleteViewModel extends ViewModel {
    private BoardShowDetailDeleteRepository repository;

    public BoardDeleteViewModel() {
        repository = new BoardShowDetailDeleteRepository();
    }

    public void BoardDetailDelete(int board_id, BoardDeleteInterface deleteInterface) {
        Log.e("BoardDetail", "222222222222222");
        repository.BoardDetailDelete(board_id , deleteInterface);
    }
}
