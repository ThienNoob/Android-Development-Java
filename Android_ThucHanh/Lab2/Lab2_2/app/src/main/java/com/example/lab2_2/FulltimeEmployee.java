package com.example.lab2_2;

public class FulltimeEmployee extends Employee{

    FulltimeEmployee(String name, String id) {
        super(name, id);
    }

    @Override
    public float TinhLuong()
    {
        return 500;
    }
    @Override
    public String toString()
    {
        return name + " -- " + id + " -- " + TinhLuong();
    }
}
