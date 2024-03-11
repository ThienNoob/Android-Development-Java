package com.example.taskpro_signin;

public class User {
    private String username;
    private String email;
    private String password;

    public User() {
        // Constructor mặc định cần thiết cho Firebase
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}