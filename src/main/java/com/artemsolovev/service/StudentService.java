package com.artemsolovev.service;

import com.artemsolovev.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> get(String grade);
}
