package com.example.komekko.model;

import java.io.Serializable;

public class Answer implements Serializable {
    private int id;
    private String content;
    private int isRightOption;
    private String blankAnswer;
    private Quiz q;

    public Answer() {
    }

    public Answer(String content, int isRightOption, String blankAnswer, Quiz q) {
        this.content = content;
        this.isRightOption = isRightOption;
        this.blankAnswer = blankAnswer;
        this.q = q;
    }

    public Answer(int id, String content, int isRightOption, String blankAnswer, Quiz q) {
        this.id = id;
        this.content = content;
        this.isRightOption = isRightOption;
        this.blankAnswer = blankAnswer;
        this.q = q;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsRightOption() {
        return isRightOption;
    }

    public void setIsRightOption(int isRightOption) {
        this.isRightOption = isRightOption;
    }

    public String getBlankAnswer() {
        return blankAnswer;
    }

    public void setBlankAnswer(String blankAnswer) {
        this.blankAnswer = blankAnswer;
    }

    public Quiz getQ() {
        return q;
    }

    public void setQ(Quiz q) {
        this.q = q;
    }
}
