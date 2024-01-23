package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.User;
import com.artemsolovev.model.Worker;
import com.artemsolovev.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private WorkerService workerService;
    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<Worker>> add(@PathVariable long idSchool, @RequestBody Worker worker) {
        try {
            this.workerService.add(worker, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(worker, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Worker>> get(@PathVariable long id) {
        try {
            Worker worker = this.workerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(worker, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Worker>> update(@RequestBody Worker worker) {
        try {
            if (worker.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>(null, "Incorrect format id"),
                        HttpStatus.BAD_REQUEST);
            }
            Worker workerNew = this.workerService.update(worker);
            return new ResponseEntity<>(new ResponseResult<>(workerNew, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
