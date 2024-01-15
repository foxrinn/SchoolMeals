package com.artemsolovev.service;

import com.artemsolovev.model.Order;

public interface OrderService {
    void add(Order order);
    Order get(long id);
}
