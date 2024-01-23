package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.Teacher;
import com.artemsolovev.model.Worker;
import com.artemsolovev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add(@RequestBody Student student, @RequestParam long idParent,
                                                       @RequestParam long idSchool) {
        try {
            this.studentService.add(student, idParent, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(student, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> get(@PathVariable long id) {
        try {
            Student student = this.studentService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(student, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{grade}")
    public ResponseEntity<ResponseResult<List<Student>>> getByGrade(@PathVariable String grade) {
        List<Student> list = this.studentService.get(grade);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Student>> update(@RequestBody Student student) {
        try {
            if (student.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>(null, "Incorrect format id"),
                        HttpStatus.BAD_REQUEST);
            }
            Student studentNew = this.studentService.update(student);
            return new ResponseEntity<>(new ResponseResult<>(studentNew, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
