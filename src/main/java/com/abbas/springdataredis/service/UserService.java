package com.abbas.springdataredis.service;

import com.abbas.springdataredis.entity.User;

import java.util.List;

public interface UserService {


    String save(User user);

    List<User> getAll();

    User findById(int id);

    String delete(int id);

    String update(User user);



}
