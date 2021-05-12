package com.example.mvvmboradtest.interfaces;

import com.example.mvvmboradtest.models.BoardModels;
import com.example.mvvmboradtest.response.BoardShowResponse;

public interface BoardSelectInterface {
    void onBoardClicked(BoardModels boardModels);
}
