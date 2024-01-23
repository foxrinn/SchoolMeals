package com.artemsolovev.service;

import com.artemsolovev.model.User;
import com.artemsolovev.model.Worker;

public interface WorkerService {
    void add(Worker worker, long idSchool);
    Worker get(long id);
    Worker update(Worker worker);
}
