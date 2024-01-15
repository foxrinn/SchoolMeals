package com.artemsolovev.service;

import com.artemsolovev.model.Parent;
import com.artemsolovev.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl implements ParentService {
    private ParentRepository parentRepository;
    @Autowired
    public void setParentRepository(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public void add(Parent parent) {
        try {
            this.parentRepository.save(parent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this parent");
        }
    }
}
