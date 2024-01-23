package com.artemsolovev.service;

import com.artemsolovev.model.Admin;
import com.artemsolovev.model.Teacher;
import com.artemsolovev.repository.AdminRepository;
import com.artemsolovev.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private SchoolService schoolService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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
    public void add(Admin admin, long idSchool) {
        try {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setSchool(this.schoolService.get(idSchool));
            this.adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this admin");
        }
    }
}
