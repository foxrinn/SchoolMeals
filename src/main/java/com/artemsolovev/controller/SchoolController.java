package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.School;
import com.artemsolovev.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SchoolController {
    private SchoolService schoolService;
    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<School>> add(@RequestBody School school) {
        try {
            this.schoolService.add(school);
            return new ResponseEntity<>(new ResponseResult<>(school, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<School>> get(@PathVariable long id) {
        try {
            School school = this.schoolService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(school, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
