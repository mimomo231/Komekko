package com.example.komekko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DevelopmentActivity extends AppCompatActivity {
    Button btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development);
        btNext = findViewById(R.id.btNext);
        btNext.setOnClickListener(v -> {
            startActivity(new Intent(DevelopmentActivity.this, Home.class));
        });
    }
}