package com.artemsolovev.service;

import com.artemsolovev.model.Parent;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.Worker;
import com.artemsolovev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private ParentService parentService;
    private SchoolService schoolService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Autowired
    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
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
    public void add(Student student, long idParent, long idSchool) {
        try {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            student.setParent(this.parentService.get(idParent));
            student.setSchool(this.schoolService.get(idSchool));
            this.studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this student");
        }
    }
    @Override
    public List<Student> get(String grade) {
        return this.studentRepository.getAllByGrade(grade);
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student does not exists!"));
    }

    @Override
    public Student update(Student student) {
        try {
            Student old = this.get(student.getId());
            old.setLogin(student.getLogin());
            old.setPassword(student.getPassword());
            old.setSurname(student.getSurname());
            old.setName(student.getName());
            old.setPatronymic(student.getPatronymic());
            old.setTableNumber(student.getTableNumber());
            old.setGrade(student.getGrade());
            this.studentRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Student has already added!");
        }
    }
}
