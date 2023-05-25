package com.example.komekko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {
    Button btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btNext = findViewById(R.id.btNext);
        btNext.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, Home.class));
        });
    }
}