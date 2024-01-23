package com.artemsolovev.service;

import com.artemsolovev.model.Dish;

import java.util.List;

public interface DishService {
    void add(Dish dish, long idSchool);
    Dish get(long id);
    List<Dish> get(String category);
    Dish update(Dish dish);
    Dish delete(long id);
}
