package com.example.demo.dto;

/**
 * Created by Administrator on 2018/4/8.
 */
public class User {
    private String phonenumber;
    private String name;
    private String password;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [phonenumber=" + phonenumber + ", name=" + name + ", password=" + password + "]";
    }
}
