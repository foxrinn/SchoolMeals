package com.artemsolovev.service;

import com.artemsolovev.model.User;

import java.util.List;

public interface UserService {
    List<User> get();
    List<User> get(long idSchool);
}
