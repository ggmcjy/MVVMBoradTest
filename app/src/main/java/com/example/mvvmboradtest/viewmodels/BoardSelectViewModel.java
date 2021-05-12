package com.example.mvvmboradtest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmboradtest.interfaces.BoardSelectInterface;
import com.example.mvvmboradtest.repositories.BoardSelectRepository;
import com.example.mvvmboradtest.response.BoardShowResponse;

public class BoardSelectViewModel extends ViewModel {
    private BoardSelectRepository boardSelectRepository;

    public BoardSelectViewModel() {
        boardSelectRepository = new BoardSelectRepository();
    }

    public LiveData<BoardShowResponse> getBoardShow(int user) {
        return boardSelectRepository.getBoardShow(user);
    }
}
