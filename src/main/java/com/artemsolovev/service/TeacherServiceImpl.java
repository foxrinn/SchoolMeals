package com.artemsolovev.service;

import com.artemsolovev.model.Teacher;
import com.artemsolovev.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public void add(Teacher teacher) {
        try {
            this.teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this teacher");
        }
    }
}
