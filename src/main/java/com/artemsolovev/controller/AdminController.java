package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Admin;
import com.artemsolovev.model.Teacher;
import com.artemsolovev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<Admin>> add(@PathVariable long idSchool, @RequestBody Admin admin) {
        try {
            this.adminService.add(admin, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(admin, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
