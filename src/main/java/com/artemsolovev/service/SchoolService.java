package com.artemsolovev.service;

import com.artemsolovev.model.School;

import java.util.List;

public interface SchoolService {
    void add(School school);
    List<School> get();
    School get(long id);
}
