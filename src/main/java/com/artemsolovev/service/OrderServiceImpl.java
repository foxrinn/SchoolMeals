package com.artemsolovev.service;

import com.artemsolovev.model.Order;
import com.artemsolovev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Order order) {
        try {
            this.orderRepository.save(order);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not add this order");
        }
    }

    @Override
    public Order get(long id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order is not exists!"));
    }
}
