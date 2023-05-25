package com.example.komekko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.komekko.adapter.VocabularyAdapter;
import com.example.komekko.dal.Sqlite;
import com.example.komekko.model.Lesson;
import com.example.komekko.model.Practice;
import com.example.komekko.model.Quiz;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PronounceActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView imgBack, imgSound;
    TextView tvWord, tvPronounce, tvMean, tvExample;
    Button btNext;
    LinearLayout layRoot;
    TextToSpeech tts;
    Sqlite db;
    ArrayList<Quiz> quizList;
    int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronounce);
        imgBack = findViewById(R.id.imgBack);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dictionary);

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
            } else
                return false;
        });
        //End Bottom Nav Menu
        imgBack.setOnClickListener(v -> finish());

        initView();
        Intent intent = getIntent();
        Lesson lesson = (Lesson) intent.getSerializableExtra("lesson");
        quizList = db.getQuizByLessonId(lesson.getId());
        currentPosition=0;
        setData(currentPosition);
        imgSound.setOnClickListener(v -> {
            tts = new TextToSpeech(PronounceActivity.this, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                    tts.setVoice(new Voice("en-us-x-sfg#male_2-local",
                            new Locale("en", "US"),
                            Voice.QUALITY_HIGH,
                            Voice.LATENCY_NORMAL,
                            false,
                            null));
                    tts.speak(tvWord.getText().toString()
                            , TextToSpeech.QUEUE_FLUSH, null);
                }
            });
            btNext.setEnabled(true);
            btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
        });
        btNext.setOnClickListener(v -> {
            if (currentPosition == 4) {
                Intent intent1 = new Intent(this, SelectAnswerActivity.class);
                intent1.putParcelableArrayListExtra("quizList", quizList);
                intent1.putExtra("lesson",lesson);
                startActivity(intent1);
            }else {
                layRoot.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {

                                // Fade in the content of the Activity
                                layRoot.animate()
                                        .alpha(1f)
                                        .setDuration(500)
                                        .setListener(null);
                            }
                        });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Your code here
                        setData(currentPosition);
                        btNext.setEnabled(false);
                        btNext.setBackgroundColor(ContextCompat.getColor(PronounceActivity.this, R.color.notReady));
                    }
                }, 500);
            }
        });
    }
    private void setData (int position) {
        tvWord.setText(quizList.get(position).getQuestion());
        tvPronounce.setText(quizList.get(position).getQuestion());
        tvMean.setText(quizList.get(position).getExplanation());
        tvExample.setText(quizList.get(position).getQuestion());
        currentPosition += 1;
    }
    private void initView() {
        layRoot = findViewById(R.id.layRoot);
        imgSound = findViewById(R.id.img);
        tvWord = findViewById(R.id.tvWord);
        tvPronounce = findViewById(R.id.tvPronounce);
        tvMean = findViewById(R.id.tvMean);
        tvExample = findViewById(R.id.tvExample);
        btNext = findViewById(R.id.btNext);
        quizList = new ArrayList<>();
        db = new Sqlite(this);
        btNext.setEnabled(false);
        btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.notReady));
    }


}