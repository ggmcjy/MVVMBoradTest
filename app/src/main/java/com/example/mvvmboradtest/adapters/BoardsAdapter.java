package com.example.mvvmboradtest.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmboradtest.R;
import com.example.mvvmboradtest.activities.SelectAllActivity;
import com.example.mvvmboradtest.databinding.ItemContainerBoardShowBinding;
import com.example.mvvmboradtest.interfaces.BoardSelectInterface;
import com.example.mvvmboradtest.models.BoardModels;

import java.util.List;

public class BoardsAdapter extends RecyclerView.Adapter<BoardsAdapter.BoardViewHolder> {
    private List<BoardModels> boardModels;
    private LayoutInflater layoutInflater;
    private BoardSelectInterface boardSelectInterface;

    public BoardsAdapter(List<BoardModels> boardModels, BoardSelectInterface boardSelectInterface) {
        this.boardModels = boardModels;
        this.boardSelectInterface = boardSelectInterface;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerBoardShowBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_board_show, parent, false
        );
        return new BoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bindBoard(boardModels.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e("bordModels.size" , boardModels.size()+"");
        return boardModels.size();
    }


    class BoardViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerBoardShowBinding itemContainerBoardShowBinding;

        public BoardViewHolder(ItemContainerBoardShowBinding itemContainerBoardShowBinding) {
            super(itemContainerBoardShowBinding.getRoot());
            this.itemContainerBoardShowBinding = itemContainerBoardShowBinding;
        }

        public void bindBoard(BoardModels boardModels) {
            itemContainerBoardShowBinding.setBoardModel(boardModels);
            itemContainerBoardShowBinding.executePendingBindings();
            itemContainerBoardShowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boardSelectInterface.onBoardClicked(boardModels);
                }
            });
        }
    }
}
