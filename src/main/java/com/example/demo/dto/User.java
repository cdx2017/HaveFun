package com.example.demo.dto;

import lombok.Data;

/**
 *
 * @author cdx
 * @date 2018/4/8
 */
@Data
public class User {
    private String phonenumber;
    private String name;
    private String password;
    private String nextgo;

    @Override
    public String toString() {
        return "User [phonenumber=" + phonenumber + ", name=" + name + ", password=" + password + "]";
    }
}
