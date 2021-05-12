package com.example.mvvmboradtest.viewmodels;


import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.BoardCreateInterface;
import com.example.mvvmboradtest.repositories.BoardCreateRepository;

public class BoardCreateViewModel extends ViewModel {
    private BoardCreateRepository repository;

    public BoardCreateViewModel() {
        repository = new BoardCreateRepository();
    }

    public void getBoardCreateResponse(int board_user_id, String board_title, String board_content, BoardCreateInterface boardCreateInterface ) {
        repository.getBoardCreateResponse(board_user_id, board_title, board_content, boardCreateInterface);
    }
}
