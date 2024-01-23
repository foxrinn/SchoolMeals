package com.artemsolovev.service;

import com.artemsolovev.model.Dish;
import com.artemsolovev.model.Order;
import com.artemsolovev.repository.OrderRepository;
import com.artemsolovev.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private StudentService studentService;
    private ParentService parentService;
    private DishService dishService;
    private SchoolService schoolService;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }
    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public void add(Order order, long idStudent, long idParent, long idSchool) {
        try {
            order.setStudent(this.studentService.get(idStudent));
            order.setParent(this.parentService.get(idParent));
            order.setSchool(this.schoolService.get(idSchool));
            this.orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Could not add this order");
        }
    }

    @Override
    public Order get(long id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order is not exists!"));
    }

    @Override
    public Order addDish(long idOrder, long idDish) {
        Order order = this.get(idOrder);
        List<Dish> dishes = order.getDishes();
        dishes.add(this.dishService.get(idDish));
        order.setDishes(dishes);
        return order;
    }

    @Override
    public List<Order> getActiveOrdersBySchool(LocalDate dateNow, long idSchool) {
        return this.orderRepository.findAllByLocalDateBeforeAndSchool_Id(dateNow, idSchool);
    }

    @Override
    public List<Order> getActiveOrdersByGrade(LocalDate dateNow, String grade) {
        return this.orderRepository.findAllByLocalDateBeforeAndStudent_Grade(dateNow, grade);
    }

    @Override
    public List<Order> getActiveOrdersByFamily(LocalDate dateNow, long idParent) {
        return this.orderRepository.findAllByLocalDateBeforeAndParent_Id(dateNow, idParent);
    }

    @Override
    public List<Order> getOrdersByFamily(long idParent) {
        return this.orderRepository.findAllByParent_Id(idParent);
    }


}
