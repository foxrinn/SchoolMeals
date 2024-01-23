package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Teacher;
import com.artemsolovev.model.User;
import com.artemsolovev.model.Worker;
import com.artemsolovev.service.TeacherService;
import com.artemsolovev.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<Teacher>> add(@PathVariable long idSchool, @RequestBody Teacher teacher) {
        try {
            this.teacherService.add(teacher, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(teacher, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Teacher>> get(@PathVariable long id) {
        try {
            Teacher teacher = this.teacherService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(teacher, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/search/{idSchool}")
    public ResponseEntity<ResponseResult<List<Teacher>>> getBySchool(@PathVariable long idSchool) {
        List<Teacher> list = this.teacherService.getTeachersBySchool(idSchool);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Teacher>> update(@RequestBody Teacher teacher) {
        try {
            if (teacher.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>(null, "Incorrect format id"),
                        HttpStatus.BAD_REQUEST);
            }
            Teacher teacherNew = this.teacherService.update(teacher);
            return new ResponseEntity<>(new ResponseResult<>(teacherNew, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
