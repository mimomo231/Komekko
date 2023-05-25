package com.example.komekko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.komekko.adapter.VocabularyAdapter;
import com.example.komekko.dal.Sqlite;
import com.example.komekko.model.Lesson;
import com.example.komekko.model.Practice;
import com.example.komekko.model.Quiz;
import com.example.komekko.model.StatusQuiz;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity implements VocabularyAdapter.VocabularyItemListener{
    private RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    ImageView imgBack;
    Sqlite db;
    private VocabularyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        recyclerView = findViewById(R.id.rv);
        imgBack = findViewById(R.id.imgBack);
        adapter = new VocabularyAdapter(this);
        db = new Sqlite(this);
        Intent intent = getIntent();
        Practice practice = (Practice) intent.getSerializableExtra("practice");
//        List<Lesson> list = db.getLessonByPracticeId(practice.getId());
        List<Lesson> list = db.getAllLesson();
        adapter.setMlist(list);
        ArrayList<StatusQuiz> sq = new ArrayList<>();
        ArrayList<Integer> numRight = new ArrayList<>();
        for (Lesson l : list) {
            sq = db.getStatusQuizByLessonId(l.getId());
            if (sq.size() > 0){
                Integer c = sq.stream()
                        .mapToInt(o -> o.getHasDoneRight())
                        .sum();
                numRight.add(c);
            }else {
                numRight.add(0);
            }
        }
        adapter.setNum(numRight);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,RecyclerView.VERTICAL,
                false));
        recyclerView.setAdapter(adapter);
        adapter.setmVocabularyItem(this);
        //========================khoi tao data===============================================
        //insert data into table practice
//        db.insertPractice(new Practice("Vocabulary","Vocabulary"));
//        db.insertPractice(new Practice("Listening","Listening"));
//        db.insertPractice(new Practice("Reading","Reading"));
//        // insert into table lesson
//        Practice p = new Practice();
//        p.setId(1);
//        db.insertLesson(new Lesson("Work","meo1.jpg",p));
//        db.insertLesson(new Lesson("Entertainment","meo1.jpg",p));
//        p.setId(2);
//        db.insertLesson(new Lesson("Work: Listening","meo1.jpg",p));
//        db.insertLesson(new Lesson("Entertainment: Listening","meo1.jpg",p));
//        p.setId(3);
//        db.insertLesson(new Lesson("Work: Reading","meo1.jpg",p));
//        db.insertLesson(new Lesson("Entertainment: Reading","meo1.jpg",p));
//        //insert into table quiz
//        Lesson l = new Lesson();
//        l.setId(1);
//        db.insertQuiz(new Quiz("người lao động", "employee","word",l));
//        db.insertQuiz(new Quiz("có hiệu quả", "efficient","word",l));
//        db.insertQuiz(new Quiz("kỹ sư", "engineer","word",l));
//        db.insertQuiz(new Quiz("kiến trúc sư", "architect","word",l));
//
//        db.insertQuiz(new Quiz("người lao động", "employee","select",l));
//        db.insertQuiz(new Quiz("có hiệu quả", "efficient","select",l));
//        db.insertQuiz(new Quiz("kỹ sư", "engineer","select",l));
//        db.insertQuiz(new Quiz("kiến trúc sư", "architect","select",l));
//
//        db.insertQuiz(new Quiz("người lao động", "employee","blank",l));
//        db.insertQuiz(new Quiz("có hiệu quả", "efficient","blank",l));
//        db.insertQuiz(new Quiz("kỹ sư", "engineer","blank",l));
//        db.insertQuiz(new Quiz("kiến trúc sư", "architect","blank",l));
//
//        l.setId(2);
//        db.insertQuiz(new Quiz("vui vẻ", "fun","word",l));
//        db.insertQuiz(new Quiz("trình diễn", "show","word",l));
//        db.insertQuiz(new Quiz("thể thao", "sport","word",l));
//        db.insertQuiz(new Quiz("dụng cụ", "instrument","word",l));
//
//        db.insertQuiz(new Quiz("vui vẻ", "fun","select",l));
//        db.insertQuiz(new Quiz("trình diễn", "show","select",l));
//        db.insertQuiz(new Quiz("thể thao", "sport","select",l));
//        db.insertQuiz(new Quiz("dụng cụ", "instrument","select",l));
//
//        db.insertQuiz(new Quiz("vui vẻ", "fun","blank",l));
//        db.insertQuiz(new Quiz("trình diễn", "show","blank",l));
//        db.insertQuiz(new Quiz("thể thao", "sport","blank",l));
//        db.insertQuiz(new Quiz("dụng cụ", "instrument","blank",l));
//        //insert into table answer   kỹ sư
//        // employee efficient engineer architect
//        Quiz q = new Quiz();
//        q.setId(1);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(2);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(3);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(4);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(5);
//        db.insertAnswer(new Answer("người lao động",1,"123",q));
//        db.insertAnswer(new Answer("có hiệu quả",0,"123",q));
//        db.insertAnswer(new Answer("kỹ sư",0,"123",q));
//        db.insertAnswer(new Answer("kiến trúc sư",0,"123",q));
//        q.setId(6);
//        db.insertAnswer(new Answer("người lao động",0,"123",q));
//        db.insertAnswer(new Answer("có hiệu quả",1,"123",q));
//        db.insertAnswer(new Answer("kỹ sư",0,"123",q));
//        db.insertAnswer(new Answer("kiến trúc sư",0,"123",q));
//        q.setId(7);
//        db.insertAnswer(new Answer("người lao động",0,"123",q));
//        db.insertAnswer(new Answer("có hiệu quả",0,"123",q));
//        db.insertAnswer(new Answer("kỹ sư",1,"123",q));
//        db.insertAnswer(new Answer("kiến trúc sư",0,"123",q));
//        q.setId(8);
//        db.insertAnswer(new Answer("người lao động",0,"123",q));
//        db.insertAnswer(new Answer("có hiệu quả",0,"123",q));
//        db.insertAnswer(new Answer("kỹ sư",0,"123",q));
//        db.insertAnswer(new Answer("kiến trúc sư",1,"123",q));
//        q.setId(9);
//        db.insertAnswer(new Answer("người lao động",1,"employee",q));
//        q.setId(10);
//        db.insertAnswer(new Answer("có hiệu quả",1,"efficient",q));
//        q.setId(11);
//        db.insertAnswer(new Answer("kỹ sư",1,"engineer",q));
//        q.setId(12);
//        db.insertAnswer(new Answer("kiến trúc sư",1,"architect",q));
//
//        q.setId(13);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(14);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(15);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(16);
//        db.insertAnswer(new Answer("word",1,"123",q));
//        q.setId(17);
//        db.insertAnswer(new Answer("dụng cụ",0,"123",q));
//        db.insertAnswer(new Answer("thể thao",0,"123",q));
//        db.insertAnswer(new Answer("trình diễn",0,"123",q));
//        db.insertAnswer(new Answer("vui vẻ",1,"123",q));
//        q.setId(18);
//        db.insertAnswer(new Answer("dụng cụ",0,"123",q));
//        db.insertAnswer(new Answer("thể thao",0,"123",q));
//        db.insertAnswer(new Answer("trình diễn",1,"123",q));
//        db.insertAnswer(new Answer("vui vẻ",0,"123",q));
//        q.setId(19);
//        db.insertAnswer(new Answer("dụng cụ",0,"123",q));
//        db.insertAnswer(new Answer("thể thao",1,"123",q));
//        db.insertAnswer(new Answer("trình diễn",0,"123",q));
//        db.insertAnswer(new Answer("vui vẻ",0,"123",q));
//        q.setId(20);
//        db.insertAnswer(new Answer("dụng cụ",1,"123",q));
//        db.insertAnswer(new Answer("thể thao",0,"123",q));
//        db.insertAnswer(new Answer("trình diễn",0,"123",q));
//        db.insertAnswer(new Answer("vui vẻ",0,"123",q));
//        q.setId(21);
//        db.insertAnswer(new Answer("dụng cụ",1,"instrument",q));
//        q.setId(22);
//        db.insertAnswer(new Answer("thể thao",1,"sport",q));
//        q.setId(23);
//        db.insertAnswer(new Answer("trình diễn",1,"show",q));
//        q.setId(24);
//        db.insertAnswer(new Answer("vui vẻ",1,"fun",q));
        //=================end of create data=========================================
        //Bottom Nav Menu Start
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
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
//        List<Lesson> list = db.getLessonByPracticeId(1);
        List<Lesson> list = db.getAllLesson();
        adapter.setMlist(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        Lesson lesson = adapter.getItem(position);
        ArrayList<Quiz> quizList = db.getQuizByLessonId(lesson.getId());
        if (quizList.size()>0){
            Intent intent = new Intent(this, PronounceActivity.class);
            intent.putExtra("lesson",lesson);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, DevelopmentActivity.class));
        }

    }
}