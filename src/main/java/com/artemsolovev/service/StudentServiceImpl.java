package com.artemsolovev.service;

import com.artemsolovev.model.Student;
import com.artemsolovev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Student> get(String grade) {
        return this.studentRepository.getAllByGrade(grade);
    }
}
