package com.example.newfirebase;

public class User {
    public String username;
    public String email;

    public User(String username, String email, String phone) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
