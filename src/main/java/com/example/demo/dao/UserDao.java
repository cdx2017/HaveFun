package com.example.demo.dao;

import com.example.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author cdx
 * @date 2018/4/8
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = " select * from t_User ";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, new Object[]{});
        while (rows.next()) {
            User user = new User();
            list.add(user);
            user.setPhonenumber(rows.getString("User_phonenumber"));
            user.setName(rows.getString("User_name"));
            user.setPassword(rows.getString("User_password"));
        }
        return list;
    }*/

    public User getName(String name) {
        User user = null;
        String sql = " select * from f_user where name = ? ";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, new Object[]{name});
        while (rows.next()) {
            user = new User();
            user.setName(rows.getString("name"));
            user.setPassword(rows.getString("password"));
        }
        return user;
    }

    public User getPhonenumber(String phonenumber) {
        User user = null;
        String sql = " select * from f_user where phonenumber = ? ";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, new Object[]{phonenumber});
        while (rows.next()) {
            user = new User();
            user.setPhonenumber(rows.getString("phonenumber"));
            user.setName(rows.getString("name"));
            user.setPassword(rows.getString("password"));
        }
        return user;
    }

    public String insert(User user) {
        /*String id = UUID.randomUUID().toString();*/
        String sql = " insert into f_user ( phonenumber , name , password) values (?,?,?) ";
        /*jdbcTemplate.update(sql, new Object[]{id, User.no, new java.sql.Date(User.date.getTime())});*/
        jdbcTemplate.update(sql, new Object[]{user.getPhonenumber(), user.getName(), user.getPassword()});
        return user.getPhonenumber();
    }

    public String update(User user) {
        String sql = " update f_user set password = ?  where phonenumber = ? ";
        jdbcTemplate.update(sql, new Object[]{user.getPassword(), user.getPhonenumber()});
        return user.getPhonenumber();
    }

    public void delete(String phonenumber) {
        String sql = " delete from f_user where phonenumber = ? ";
        jdbcTemplate.update(sql, new Object[]{phonenumber});
    }
}
