package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Dish;
import com.artemsolovev.model.School;
import com.artemsolovev.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private DishService dishService;
    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(path = "/{idSchool}")
    public ResponseEntity<ResponseResult<Dish>> add(@RequestBody Dish dish, @PathVariable long idSchool) {
        try {
            this.dishService.add(dish, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(dish, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Dish>> get(@PathVariable long id) {
        try {
            Dish dish = this.dishService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(dish, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{category}")
    public ResponseEntity<ResponseResult<List<Dish>>> get(@PathVariable String category){
        try {
            List<Dish> dishes = this.dishService.get(category);
            return new ResponseEntity<>(new ResponseResult<>(dishes, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Dish>> update(@RequestBody Dish dish) {
        try {
            if (dish.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>(null, "Incorrect format id"),
                        HttpStatus.BAD_REQUEST);
            }
            Dish dishNew = this.dishService.update(dish);
            return new ResponseEntity<>(new ResponseResult<>(dishNew, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Dish>> delete(@PathVariable long id) {
        try {
            Dish dish = this.dishService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(dish, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
