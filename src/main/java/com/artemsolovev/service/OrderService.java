package com.artemsolovev.service;

import com.artemsolovev.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void add(Order order, long idStudent, long idParent, long idSchool);
    Order get(long id);
    Order addDish(long idOrder, long idDish);
    List<Order> getActiveOrdersBySchool(LocalDate dateNow, long idSchool);
    List<Order> getActiveOrdersByGrade(LocalDate dateNow, String grade);
    List<Order> getActiveOrdersByFamily(LocalDate dateNow, long idParent);
    List<Order> getOrdersByFamily(long idParent);
}
