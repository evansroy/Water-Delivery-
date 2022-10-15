package com.example.waterdelivery.Models;

public class User {

    public String userName;
    public String userPassword;
    public String userPhoneNo;
    public String userEmail;

    public User() {

    }

    public User(String userName, String userPassword, String userPhoneNo, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNo = userPhoneNo;
        this.userEmail = userEmail;
    }
}
