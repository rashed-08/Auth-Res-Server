package com.web.springsecurity.service;


import com.web.springsecurity.model.User;

public interface UserServices {
    User findUserByUsername(String username);
}
