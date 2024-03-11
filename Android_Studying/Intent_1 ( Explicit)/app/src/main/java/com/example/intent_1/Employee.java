package com.example.intent_1;

import java.io.Serializable;

public class Employee implements Serializable {
    public String name;
    public int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

