package com.example.komekko.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Quiz implements Serializable, Parcelable {
    private int id;
    private String explanation, question, type;
    private Lesson l;

    public Quiz(int id, String explanation, String question, String type, Lesson l) {
        this.id = id;
        this.explanation = explanation;
        this.question = question;
        this.type = type;
        this.l = l;
    }

    public Quiz(String explanation, String question, String type, Lesson l) {
        this.explanation = explanation;
        this.question = question;
        this.type = type;
        this.l = l;
    }

    public Quiz() {
    }

    protected Quiz(Parcel in) {
        id = in.readInt();
        explanation = in.readString();
        question = in.readString();
        type = in.readString();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Lesson getL() {
        return l;
    }

    public void setL(Lesson l) {
        this.l = l;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(explanation);
        dest.writeString(question);
        dest.writeString(type);
    }
}
