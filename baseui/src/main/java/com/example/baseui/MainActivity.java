package com.example.baseui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView mCircleProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCircleProgressView = findViewById(R.id.progress_view);
        mCircleProgressView.setValue("80",100);
    }
}