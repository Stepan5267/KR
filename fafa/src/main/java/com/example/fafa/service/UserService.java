package com.example.fafa.service;

import com.example.fafa.model.Users;  // Users, не User
import com.example.fafa.repository.UserDao;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public List<Users> findAll() {
        return userDao.findAll();
    }

    public Users findOne(final long id) {
        return userDao.findOne(id);
    }

    public void save(final Users entity) {
        if (entity == null) return;
        userDao.save(entity);
    }

    public void update(final Users entity) {
        if (entity == null) return;
        userDao.update(entity);
    }

    public void delete(final Users entity) {
        if (entity == null) return;
        userDao.delete(entity);
    }

    public void deleteById(final Long id) {
        if (id == null) return;
        userDao.deleteById(id);
    }
}