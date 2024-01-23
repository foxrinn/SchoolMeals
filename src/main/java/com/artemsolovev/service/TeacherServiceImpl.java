package com.artemsolovev.service;

import com.artemsolovev.model.Teacher;
import com.artemsolovev.model.Worker;
import com.artemsolovev.repository.TeacherRepository;
import com.artemsolovev.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    private SchoolService schoolService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Autowired
    public void setSchoolService(SchoolService schoolService){
        this.schoolService = schoolService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(Teacher teacher, long idSchool) {
        try {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacher.setSchool(this.schoolService.get(idSchool));
            this.teacherRepository.save(teacher);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this teacher");
        }
    }

    @Override
    public Teacher get(long id) {
        return this.teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher is not exists!"));
    }


    @Override
    public Teacher update(Teacher teacher) {
        try {
            Teacher old = this.get(teacher.getId());
            old.setLogin(teacher.getLogin());
            old.setPassword(teacher.getPassword());
            old.setSurname(teacher.getSurname());
            old.setName(teacher.getName());
            old.setPatronymic(teacher.getPatronymic());
            old.setSchool(teacher.getSchool());
            this.teacherRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Teacher has already added!");
        }
    }

    @Override
    public List<Teacher> getTeachersBySchool(long idSchool) {
        return this.teacherRepository.findAllBySchool_Id(idSchool);
    }
}
