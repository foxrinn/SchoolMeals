package com.artemsolovev.service;

import com.artemsolovev.model.Parent;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.Teacher;

public interface ParentService {
    void add(Parent parent, long idSchool);
    Parent get(long id);
    Parent update(Parent parent);
}
