package com.artemsolovev.service;

import com.artemsolovev.model.Worker;
import com.artemsolovev.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    private WorkerRepository workerRepository;
    @Autowired
    public void setWorkerRepository(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }
    @Override
    public void add(Worker worker) {
        try {
            this.workerRepository.save(worker);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this worker");
        }
    }
}
