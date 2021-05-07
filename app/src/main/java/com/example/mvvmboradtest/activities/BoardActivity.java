package com.example.mvvmboradtest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmboradtest.R;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        int _id = getIntent().getIntExtra("user_id", 0);
        Log.e("BoardActivity", _id + "");
    }
}