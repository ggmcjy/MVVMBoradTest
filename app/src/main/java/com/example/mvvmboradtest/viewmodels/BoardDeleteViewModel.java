package com.example.mvvmboradtest.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.BoardDeleteInterface;
import com.example.mvvmboradtest.repositories.BoardShowDetailDeleteRepository;

public class BoardDeleteViewModel extends ViewModel {
    private BoardShowDetailDeleteRepository repository;

    public BoardDeleteViewModel() {
        repository = new BoardShowDetailDeleteRepository();
    }

    public void BoardDetailDelete(int board_id, BoardDeleteInterface deleteInterface) {
        repository.BoardDetailDelete(board_id , deleteInterface);
    }
}
