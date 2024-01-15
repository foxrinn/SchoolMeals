package com.artemsolovev.service;

import com.artemsolovev.model.Dish;
import com.artemsolovev.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private DishRepository dishRepository;
    @Autowired
    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public void add(Dish dish) {
        try {
            this.dishRepository.save(dish);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this dish");
        }
    }

    @Override
    public Dish get(long id) {
        return this.dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Dish is not exists!"));
    }

    @Override
    public List<Dish> get(String category) {
        return this.dishRepository.findAllByCategory(category);
    }

    @Override
    public Dish update(Dish dish) {
        try {
            Dish old = this.get(dish.getId());
            old.setName(dish.getName());
            old.setPictureURL(dish.getPictureURL());
            old.setPrice(dish.getPrice());
            old.setWeight(dish.getWeight());
            old.setCategory(dish.getCategory());
            old.setDescription(dish.getDescription());
            old.setIngredients(dish.getIngredients());
            old.setAllergens(dish.getAllergens());
            old.setCalories(dish.getCalories());
            this.dishRepository.save(old);
            return old;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Dish has already added!");
        }
    }
}
