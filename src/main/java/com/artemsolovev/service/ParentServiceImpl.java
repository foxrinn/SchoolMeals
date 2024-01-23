package com.artemsolovev.service;

import com.artemsolovev.model.Parent;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.Teacher;
import com.artemsolovev.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl implements ParentService {
    private ParentRepository parentRepository;
    private SchoolService schoolService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setParentRepository(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }
    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(Parent parent, long idSchool) {
        try {
            parent.setPassword(passwordEncoder.encode(parent.getPassword()));
            parent.setSchool(this.schoolService.get(idSchool));
            this.parentRepository.save(parent);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this parent");
        }
    }

    @Override
    public Parent get(long id) {
        return this.parentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Parent does not exists!"));
    }

    @Override
    public Parent update(Parent parent) {
        try {
            Parent old = this.get(parent.getId());
            old.setLogin(parent.getLogin());
            old.setPassword(parent.getPassword());
            old.setSurname(parent.getSurname());
            old.setName(parent.getName());
            old.setPatronymic(parent.getPatronymic());
            this.parentRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Parent has already added!");
        }
    }
}
