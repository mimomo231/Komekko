package com.example.komekko;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dictionary extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private EditText etTranslateInput;
    private Button btnTranslate;
    private TextView etTranslateOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        btnTranslate = findViewById(R.id.btnTranslate);
        etTranslateInput = findViewById(R.id.etTranslateInput);
//        etTranslateOutput = findViewById(R.id.etTranslateOutput);



        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = etTranslateInput.getText().toString();

                final String translate = "https://translate.google.com/?sl=en&tl=vi&text="+temp+"&op=translate";
                try {
                    WebBrowser.WEBSITE_LINK = translate;
                    WebBrowser.WEBSITE_TITLE = "Google Translate";
                    Intent targetActivity = new Intent(Dictionary.this, WebBrowser.class);
                    startActivity(targetActivity);
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(translate)));
                }

//                etTranslateOutput.setText(temp);

            }
        });

        //Bottom Nav Menu Start
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dictionary);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.dictionary) {
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                return true;
            } else
                return false;
        });
        //End Bottom Nav Menu

    }
}