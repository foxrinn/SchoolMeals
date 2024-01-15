package com.artemsolovev.service;

import com.artemsolovev.model.User;
import com.artemsolovev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this user");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User is not exists!"));
    }

    @Override
    public User update(User user) {
        try {
            User old = this.get(user.getId());
            old.setLogin(user.getLogin());
            old.setPassword(user.getPassword());
            old.setSurname(user.getSurname());
            old.setName(user.getName());
            old.setPatronymic(user.getPatronymic());
            this.userRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User has already added!");
        }
    }
}
