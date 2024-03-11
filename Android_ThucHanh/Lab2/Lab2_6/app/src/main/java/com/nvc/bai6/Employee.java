package com.nvc.bai6;

public class Employee {
    String ID;
    String name;
    boolean isManager;

    public Employee(String ID, String name, boolean isManager) {
        this.ID = ID;
        this.name = name;
        this.isManager = isManager;
    }

    public String getFullName(){
        return name;
    }

    public boolean isManager() {
        return isManager;
    }
}

