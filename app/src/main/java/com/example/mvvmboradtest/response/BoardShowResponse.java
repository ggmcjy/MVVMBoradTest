package com.example.mvvmboradtest.response;

import com.example.mvvmboradtest.models.BoardModels;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardShowResponse {

    @SerializedName("board_total")
    public Integer total;

    @SerializedName("board_data")
    public List<BoardModels> boardModels;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BoardModels> getBoardModels() {
        return boardModels;
    }

    public void setBoardModels(List<BoardModels> boardModels) {
        this.boardModels = boardModels;
    }
}
