package com.example.liststatus;

public class Tag {
    public int id;
    public String name;
    public int status; // 0 for chưa đặt lịch, 1 cho đang thực hiện, 2 cho đã hoàn thành, 3 cho trễ hạn

    public Tag(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
