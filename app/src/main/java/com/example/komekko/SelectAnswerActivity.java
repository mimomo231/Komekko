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
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.komekko.dal.Sqlite;
import com.example.komekko.model.Answer;
import com.example.komekko.model.Lesson;
import com.example.komekko.model.Quiz;
import com.example.komekko.model.StatusQuiz;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SelectAnswerActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView imgBack, imgSound;
    TextView tvA, tvB, tvC, tvD;
    Button btNext;
    LinearLayout layRoot;
    TextToSpeech tts;
    Sqlite db;
    ArrayList<Quiz> quizList;
    ArrayList<Answer> answers;
    int currentPosition;
    Lesson lesson;
    ArrayList<Answer> curAnswer;
    ArrayList<StatusQuiz> statusList;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_answer);
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
        imgSound.setOnClickListener(v -> {
            tts = new TextToSpeech(SelectAnswerActivity.this, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                    tts.setVoice(new Voice("en-us-x-sfg#male_2-local",
                            new Locale("en", "US"),
                            Voice.QUALITY_HIGH,
                            Voice.LATENCY_NORMAL,
                            false,
                            null));
                    tts.speak(quizList.get(currentPosition-1).getQuestion(),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                }
            });
        });
        btNext.setOnClickListener(v -> {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String formattedDate = formatter.format(date);
            statusList.add(new StatusQuiz(check,formattedDate,quizList.get(currentPosition-1)));
            if (currentPosition == 8) {
                for (StatusQuiz s:
                     statusList) {
                    db.insertStatusQuiz(s);
                }
                Intent intent1 = new Intent(this, FillBlankActivity.class);
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
                        btNext.setBackgroundColor(ContextCompat.getColor(SelectAnswerActivity.this, R.color.notReady));
                    }
                }, 500);

            }
        });
        tvA.setOnClickListener(v -> {
            setEnableClickText(false);
            if (curAnswer.get(0).getIsRightOption() == 1) {
                tvA.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.right);
                mediaPlayer.start();
                check=1;
            } else {
                tvA.setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer));
                showRightAnswer();
            }
            btNext.setEnabled(true);
            btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
        });
        tvB.setOnClickListener(v -> {
            setEnableClickText(false);
            if (curAnswer.get(1).getIsRightOption() == 1) {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.right);
                mediaPlayer.start();
                tvB.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                check=1;
            } else {
                tvB.setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer));
                showRightAnswer();
            }
            btNext.setEnabled(true);
            btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
        });
        tvC.setOnClickListener(v -> {
            setEnableClickText(false);
            if (curAnswer.get(2).getIsRightOption() == 1) {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.right);
                mediaPlayer.start();
                tvC.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                check=1;
            } else {
                tvC.setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer));
                showRightAnswer();
            }
            btNext.setEnabled(true);
            btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
        });
        tvD.setOnClickListener(v -> {
            setEnableClickText(false);
            if (curAnswer.get(3).getIsRightOption() == 1) {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.right);
                mediaPlayer.start();
                tvD.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                check=1;
            } else {
                tvD.setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer));
                showRightAnswer();
            }
            btNext.setEnabled(true);
            btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
        });
    }

    private void setEnableClickText(boolean b) {
        tvA.setEnabled(b);
        tvB.setEnabled(b);
        tvC.setEnabled(b);
        tvD.setEnabled(b);
    }

    private void showRightAnswer() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
        mediaPlayer.start();
        for (Answer a: curAnswer) {
            if (a.getIsRightOption() == 1){
                if (a.getId() % 4 == 1) {
                    tvA.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                } else if (a.getId() % 4 == 2) {
                    tvB.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                } else if (a.getId() % 4 == 3) {
                    tvC.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                } else if (a.getId() % 4 == 0) {
                    tvD.setBackgroundColor(ContextCompat.getColor(this, R.color.rightAnswer));
                }
            }
        }
    }

    private void setData(int position) {
        curAnswer = new ArrayList<>();
        for (Answer a : answers) {
            if (a.getQ().getId() == quizList.get(position).getId()) {
                curAnswer.add(a);

            } else if (a.getQ().getId() > quizList.get(position).getId()){
                break;
            }
        }
//
        tvA.setText(curAnswer.get(0).getContent());
        tvB.setText(curAnswer.get(1).getContent());
        tvC.setText(curAnswer.get(2).getContent());
        tvD.setText(curAnswer.get(3).getContent());
        tvA.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvB.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvC.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvD.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setEnableClickText(true);
//        System.out.println(answers.get(0).getId());
        check =0;
        currentPosition += 1;
    }

    private void initView() {
        layRoot = findViewById(R.id.layRoot);
        imgSound = findViewById(R.id.img);
        tvA = findViewById(R.id.tvA);
        tvB = findViewById(R.id.tvB);
        tvC = findViewById(R.id.tvC);
        tvD = findViewById(R.id.tvD);
        btNext = findViewById(R.id.btNext);
        Intent intent = getIntent();
        quizList = new ArrayList<>();
        quizList = intent.getParcelableArrayListExtra("quizList");
        lesson = (Lesson) intent.getSerializableExtra("lesson");
        statusList = new ArrayList<>();
        check=0;
        db = new Sqlite(this);
        currentPosition=4;
        answers = db.getAnswerByQuizId(quizList.get(currentPosition).getId());
        btNext.setEnabled(false);
        btNext.setBackgroundColor(ContextCompat.getColor(this, R.color.notReady));
    }
}