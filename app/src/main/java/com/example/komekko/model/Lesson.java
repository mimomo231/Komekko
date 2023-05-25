package com.example.komekko.model;

import java.io.Serializable;

public class Lesson implements Serializable {
    private int id;
    private String name, avatar;
    private Practice p;

    public Lesson(int id, String name, String avatar, Practice p) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.p = p;
    }

    public Lesson(String name, String avatar, Practice p) {
        this.name = name;
        this.avatar = avatar;
        this.p = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Practice getP() {
        return p;
    }

    public void setP(Practice p) {
        this.p = p;
    }

    public Lesson() {
    }
}
