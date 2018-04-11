package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.Message;
import com.example.demo.dto.User;
import com.example.demo.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018/4/8.
 */

@Controller
public class MyController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index1() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/modify")
    public String modify() {
        return "modify";
    }


    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User uniqueUser) {
        if (userDao.getName(uniqueUser.getName()) != null && userDao.getName(uniqueUser.getName()).getPassword().equals(uniqueUser.getPassword())) {
            return uniqueUser;
        } else {
            return null;
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody User uniqueUser) {
        if (userDao.getPhonenumber(uniqueUser.getPhonenumber()) == null) {
            if (userDao.insert(uniqueUser) != null) {
                return uniqueUser;
            } else {
                return null;
            }
        }
        uniqueUser.setPhonenumber("yijingcunzai");
        return uniqueUser;
    }

    @PostMapping("/modify")
    @ResponseBody
    public Object modify(@RequestBody User uniqueUser) {
        if (userDao.getPhonenumber(uniqueUser.getPhonenumber()) != null) {
            if (userDao.update(uniqueUser) != null) {
                return uniqueUser;
            } else {
                return null;
            }
        }
        uniqueUser.setPhonenumber("gaihaomaweizhuce");
        return uniqueUser;
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestBody String phonenumber) {
        String strURL = "http://localhost:8080/api/v1/requestSmsCode/" + phonenumber;
        return HttpUtil.sendGet(strURL);
    }

    @PostMapping("/check")
    @ResponseBody
    public String check(@RequestBody Message message) {
        String strURL = "http://localhost:8080/api/v1/verifyImgCode";
        String strParam = "imgCode= " + message.getCheckword();
        String a = HttpUtil.sendPost2(strURL, strParam);
        System.out.println(a);
        return a;
    }
}
