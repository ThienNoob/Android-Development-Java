package com.example.lab2_4;

public class Employee {
    public String id;
    public String fullname;

    public boolean manager;

    Employee(String id, String fullname, Boolean manager) {
        this.id = id;
        this.fullname = fullname;
        this.manager= manager;

    }

    public boolean isManager() {
        return manager;
    }

    public String getFullName() {
        return fullname;
    }

}
