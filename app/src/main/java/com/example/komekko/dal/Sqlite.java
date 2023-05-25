package com.example.komekko.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.komekko.model.*;

import java.util.ArrayList;
import java.util.List;

public class Sqlite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "english.db";
    private static final int DATABASE_VERSION = 1;
    private static final String tbPractice = "practice";
    private static final String tbLesson = "lesson";
    private static final String tbQuiz = "quiz";
    private static final String tbStatusQuiz = "status_quiz";
    private static final String tbAnswer = "answer";

    public Sqlite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String sql = "CREATE TABLE " + tbPractice + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "type TEXT)";
        db.execSQL(sql);
        sql = "CREATE TABLE " + tbLesson + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "avatar TEXT," +
                "practice_id INTEGER," +
                "FOREIGN KEY(practice_id) REFERENCES "+ tbPractice + "(id))";
        db.execSQL(sql);
        sql = "CREATE TABLE " + tbQuiz + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "explanation TEXT," +
                "question TEXT," +
                "type TEXT," +
                "lesson_id INTEGER," +
                "FOREIGN KEY(lesson_id) REFERENCES "+ tbLesson + "(id))";
        db.execSQL(sql);
        sql = "CREATE TABLE " + tbStatusQuiz + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "has_done_right INTEGER," +
                "on_date TEXT," +
                "type TEXT," +
                "quiz_id INTEGER," +
                "FOREIGN KEY(quiz_id) REFERENCES "+ tbQuiz + "(id))";
        db.execSQL(sql);
        sql = "CREATE TABLE " + tbAnswer + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content TEXT," +
                "is_right_option INTEGER," +
                "blank_answer TEXT," +
                "quiz_id INTEGER," +
                "FOREIGN KEY(quiz_id) REFERENCES "+ tbQuiz + "(id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //========================table practice==============================
    public long insertPractice(Practice p){
        ContentValues values=new ContentValues();
        values.put("name",p.getName());
        values.put("type",p.getType());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("practice",null,values);
    }
    //=================table lession=======================================
    public long insertLesson(Lesson l){
        ContentValues values=new ContentValues();
        values.put("name",l.getName());
        values.put("avatar",l.getAvatar());
        values.put("practice_id",l.getP().getId());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("lesson",null,values);
    }
    public List<Lesson> getAllLesson(){
        List<Lesson> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "id";
        Cursor rs = st.query("lesson",null,null,null,
                null, null,order);
        while(rs!=null && rs.moveToNext()){
            Practice p = new Practice();
            p.setId(rs.getInt(3));
            list.add(new Lesson(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    p));
        }
        rs.close();
        return list;
    }
    public List<Lesson> getLessonByPracticeId(int id){
        List<Lesson> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String where="practice_id=?";
        String order = "id";
        String[] agrs={Integer.toString(id)};
        Cursor rs = st.query("lesson",null,where,agrs,null, null,order);
        while(rs!=null && rs.moveToNext()){
            Practice p = new Practice();
            p.setId(rs.getInt(3));
            list.add(new Lesson(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    p));
        }
        rs.close();
        return list;
    }
    //=======================================table quiz=======================================
    public long insertQuiz(Quiz q){
        ContentValues values=new ContentValues();
        values.put("explanation",q.getExplanation());
        values.put("question",q.getQuestion());
        values.put("type",q.getType());
        values.put("lesson_id",q.getL().getId());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("quiz",null,values);
    }
    public ArrayList<Quiz> getQuizByLessonId(int id){
        ArrayList<Quiz> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String where="lesson_id=?";
        String order = "id";
        String[] agrs={Integer.toString(id)};
        Cursor rs = st.query("quiz",null,where,agrs,null, null,order);
        while(rs!=null && rs.moveToNext()){
            Lesson l = new Lesson();
            l.setId(id);
            list.add(new Quiz(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    l));
        }
        rs.close();
        return list;
    }

    //=======================================table status_quiz=======================================
    public long insertStatusQuiz(StatusQuiz s){
        ContentValues values=new ContentValues();
        values.put("has_done_right",s.getHasDoneRight());
        values.put("on_date",s.getOnDate());
        values.put("quiz_id",s.getQ().getId());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("status_quiz",null,values);
    }
    public ArrayList<StatusQuiz> getStatusQuizByLessonId(int id){
        ArrayList<StatusQuiz> list = new ArrayList<>();
        ContentValues values=new ContentValues();
        String sql = "SELECT s.has_done_right, s.quiz_id FROM status_quiz s " +
                "INNER JOIN quiz q ON (s.quiz_id = q.id) " +
                "WHERE q.lesson_id = ? " +
                "ORDER BY s.on_date DESC " +
                "LIMIT 4";
        String[] agrs={Integer.toString(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs =st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            Quiz q = new Quiz();
            q.setId(rs.getInt(1));
            StatusQuiz s = new StatusQuiz();
            s.setHasDoneRight(rs.getInt(0));
            s.setQ(q);
            list.add(s);
        }
        rs.close();
        return list;
    }
    //=======================================table answer=======================================
    public long insertAnswer(Answer a){
        ContentValues values=new ContentValues();
        values.put("content",a.getContent());
        values.put("is_right_option",a.getIsRightOption());
        values.put("blank_answer",a.getBlankAnswer());
        values.put("quiz_id",a.getQ().getId());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("answer",null,values);
    }
    public ArrayList<Answer> getAnswerByQuizId(int id){
        ArrayList<Answer> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String where="quiz_id >= ? AND quiz_id < ?";
        String order = "id";
        String[] agrs={Integer.toString(id),Integer.toString(id+4)};
        Cursor rs = st.query("answer",null,where,agrs,null, null,order);
        while(rs!=null && rs.moveToNext()){
            Quiz q = new Quiz();
            q.setId(rs.getInt(4));
            list.add(new Answer(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3),
                    q));
        }
        rs.close();
        return list;
    }
}
