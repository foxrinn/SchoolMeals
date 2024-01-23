package com.artemsolovev.service;

import com.artemsolovev.model.School;
import com.artemsolovev.model.User;
import com.artemsolovev.model.Worker;
import com.artemsolovev.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    private WorkerRepository workerRepository;
    private SchoolService schoolService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setWorkerRepository(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
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
    public void add(Worker worker, long idSchool) {
        try {
            worker.setPassword(passwordEncoder.encode(worker.getPassword()));
            worker.setSchool(this.schoolService.get(idSchool));
            this.workerRepository.save(worker);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this worker");
        }
    }

    @Override
    public Worker get(long id) {
        return this.workerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Worker is not exists!"));
    }

    @Override
    public Worker update(Worker worker) {
        try {
            Worker old = this.get(worker.getId());
            old.setLogin(worker.getLogin());
            old.setPassword(worker.getPassword());
            old.setSurname(worker.getSurname());
            old.setName(worker.getName());
            old.setPatronymic(worker.getPatronymic());
            old.setSchool(worker.getSchool());
            this.workerRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Worker has already added!");
        }
    }
}
