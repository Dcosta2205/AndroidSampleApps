package com.xyz.sqlitedatabase.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String address;
    private int id;

    public Student(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }
}
