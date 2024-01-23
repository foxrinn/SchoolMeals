package com.artemsolovev.service;

import com.artemsolovev.model.Teacher;
import com.artemsolovev.model.Worker;

import java.util.List;

public interface TeacherService {
    void add(Teacher teacher, long idSchool);
    Teacher get(long id);
    Teacher update(Teacher teacher);
    List<Teacher> getTeachersBySchool(long idSchool);
}
