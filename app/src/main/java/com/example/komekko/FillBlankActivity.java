package com.example.komekko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.komekko.dal.Sqlite;
import com.example.komekko.model.Answer;
import com.example.komekko.model.Lesson;
import com.example.komekko.model.Quiz;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FillBlankActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView imgBack, imgSound;
    TextView tvQuestion;
    EditText etAnswer;
    Button btNext, btCheck;
    LinearLayout layRoot;
    Sqlite db;
    ArrayList<Quiz> quizList;
    ArrayList<Answer> answers;
    int currentPosition;
    Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank);
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
        setData(currentPosition);
        btNext.setOnClickListener(v -> {
            if (currentPosition == 12) {
                startActivity(new Intent(FillBlankActivity.this, ResultActivity.class));
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
                        btNext.setBackgroundColor(ContextCompat.getColor(FillBlankActivity.this, R.color.notReady));
                    }
                }, 500);

            }
        });
        btCheck.setOnClickListener(v -> {
            String text = etAnswer.getText().toString();
            if (text.equalsIgnoreCase(quizList.get(currentPosition-1).getQuestion())) {
                btNext.setEnabled(true);
                btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.right);
                mediaPlayer.start();
            } else {
                btNext.setEnabled(false);
                btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.notReady));
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
                mediaPlayer.start();
            }
        });
        etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    btCheck.setEnabled(true);
                    btCheck.setBackgroundColor(ContextCompat.getColor(FillBlankActivity.this, R.color.rightAnswer));
                } else {
                    btCheck.setEnabled(false);
                    btCheck.setBackgroundColor(ContextCompat.getColor(FillBlankActivity.this, R.color.notReady));
                }
            }
        });
    }

    private void setData(int position) {
        String text = "Từ '"+quizList.get(position).getExplanation() +"' trong tiếng anh:";
        tvQuestion.setText(text);
        etAnswer.setText("");
        currentPosition += 1;
    }

    private void initView() {
        layRoot = findViewById(R.id.layRoot);
        imgSound = findViewById(R.id.img);
        btNext = findViewById(R.id.btNext);
        Intent intent = getIntent();
        quizList = new ArrayList<>();
        quizList = intent.getParcelableArrayListExtra("quizList");
        lesson = (Lesson) intent.getSerializableExtra("lesson");
        currentPosition =8;
        db = new Sqlite(this);
        answers = db.getAnswerByQuizId(quizList.get(currentPosition).getId());
        btNext.setEnabled(false);
        btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.notReady));
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btCheck = findViewById(R.id.btCheck);
        btCheck.setEnabled(false);
        btCheck.setBackgroundColor(ContextCompat.getColor(this, R.color.notReady));
    }
}