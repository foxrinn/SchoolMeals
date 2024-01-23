package com.artemsolovev.service;

import com.artemsolovev.model.School;
import com.artemsolovev.model.User;
import com.artemsolovev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> get() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> get(long idSchool) {
        return this.userRepository.findAllBySchool_Id(idSchool);
    }
}
