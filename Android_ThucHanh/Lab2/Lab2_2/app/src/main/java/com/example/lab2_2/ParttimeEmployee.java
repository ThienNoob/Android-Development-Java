package com.example.lab2_2;

public class ParttimeEmployee extends Employee{
    ParttimeEmployee(String name, String id) {
        super(name, id);
    }
    @Override
    public float TinhLuong()
    {
        return 150;
    }
    @Override
    public String toString()
    {
        return name + " -- " + id + " -- " + TinhLuong();
    }
}
