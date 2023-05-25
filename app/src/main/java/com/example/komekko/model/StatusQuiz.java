package com.example.komekko.model;

import java.io.Serializable;

public class StatusQuiz implements Serializable {
    private int id, hasDoneRight;
    private String onDate;
    private Quiz q;

    public StatusQuiz(int id, int hasDoneRight, String onDate, Quiz q) {
        this.id = id;
        this.hasDoneRight = hasDoneRight;
        this.onDate = onDate;
        this.q = q;
    }

    public StatusQuiz(int hasDoneRight, String onDate, Quiz q) {
        this.hasDoneRight = hasDoneRight;
        this.onDate = onDate;
        this.q = q;
    }

    public StatusQuiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHasDoneRight() {
        return hasDoneRight;
    }

    public void setHasDoneRight(int hasDoneRight) {
        this.hasDoneRight = hasDoneRight;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public Quiz getQ() {
        return q;
    }

    public void setQ(Quiz q) {
        this.q = q;
    }
}
