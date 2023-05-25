package com.example.komekko.model;

import java.io.Serializable;

public class Practice implements Serializable {
    private int id;
    private String name, type;

    public Practice() {
    }

    public Practice(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Practice(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}