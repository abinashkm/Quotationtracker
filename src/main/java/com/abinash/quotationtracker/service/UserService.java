package com.abinash.quotationtracker.service;

import com.abinash.quotationtracker.entity.User;

public interface UserService {

    User registerUser(String name, String email, String password, String role);
}