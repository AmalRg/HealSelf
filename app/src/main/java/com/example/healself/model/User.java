package com.example.healself.model;

public class User {
    private String email;
    private String password;
    public User(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
