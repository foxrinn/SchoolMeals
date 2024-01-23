package com.artemsolovev.service;

import com.artemsolovev.model.Student;
import com.artemsolovev.model.Worker;

import java.util.List;

public interface StudentService {
    void add(Student student, long idParent, long idSchool);
    List<Student> get(String grade);
    Student get(long id);
    Student update(Student student);
}
