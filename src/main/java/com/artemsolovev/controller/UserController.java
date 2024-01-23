package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Dish;
import com.artemsolovev.model.School;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.User;
import com.artemsolovev.service.SchoolService;
import com.artemsolovev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<User>>> get() {
        List<User> list = this.userService.get();
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @GetMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<List<User>>> get(@PathVariable long idSchool) {
        List<User> list = this.userService.get(idSchool);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }
}
