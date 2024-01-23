package com.artemsolovev.service;

import com.artemsolovev.model.School;
import com.artemsolovev.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    private SchoolRepository schoolRepository;
    @Autowired
    public void setSchoolRepository(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
    @Override
    public void add(School school) {
        try {
            this.schoolRepository.save(school);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this school");
        }
    }

    @Override
    public List<School> get() {
        return this.schoolRepository.findAll();
    }

    @Override
    public School get(long id) {
        return this.schoolRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("School is not exists!"));
    }
}
