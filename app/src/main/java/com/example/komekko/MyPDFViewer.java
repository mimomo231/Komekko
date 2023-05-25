package com.example.komekko;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyPDFViewer extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    PDFView pdfView;
    LottieAnimationView myLottie;
    ImageView imgBack;
    TextView pdfTitle;
    public  static  String PDF_ASSET_NAME = "";
    public  static  String PDF_TITLE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_page);
        pdfView = findViewById(R.id.pdfView);
        myLottie = findViewById(R.id.myLottie);
        imgBack = findViewById(R.id.imgBack);
        pdfTitle = findViewById(R.id.pdfTitle);


        pdfTitle.setText(""+PDF_TITLE);
        pdfView.setVisibility(View.INVISIBLE);
        myLottie.setVisibility(View.VISIBLE);


        pdfView.fromAsset(PDF_ASSET_NAME)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pdfView.setVisibility(View.VISIBLE);
                                myLottie.setVisibility(View.GONE);
                            }
                        }, 1000);

                    }
                })
                .load();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        //Bottom Nav Menu Start
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.dictionary) {
                startActivity(new Intent(getApplicationContext(), Dictionary.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
        //End Bottom Nav Menu





    }
}