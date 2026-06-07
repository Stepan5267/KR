package com.example.fafa.repository;

import com.example.fafa.model.Users;  // Users, не User

public class UserDao extends BaseDao<Users> {
    public UserDao() {
        super(Users.class);
    }
}