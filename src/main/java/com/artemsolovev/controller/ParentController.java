package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Parent;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.Worker;
import com.artemsolovev.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parent")
public class ParentController {
    private ParentService parentService;
    @Autowired
    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<Parent>> add(@PathVariable long idSchool, @RequestBody Parent parent) {
        try {
            this.parentService.add(parent, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(parent, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Parent>> get(@PathVariable long id) {
        try {
            Parent parent = this.parentService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(parent, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<ResponseResult<Parent>> update(@RequestBody Parent parent) {
        try {
            if (parent.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>(null, "Incorrect format id"),
                        HttpStatus.BAD_REQUEST);
            }
            Parent parentNew = this.parentService.update(parent);
            return new ResponseEntity<>(new ResponseResult<>(parentNew, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
