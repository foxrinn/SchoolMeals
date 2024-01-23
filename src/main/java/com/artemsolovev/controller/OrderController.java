package com.artemsolovev.controller;

import com.artemsolovev.dto.ResponseResult;
import com.artemsolovev.model.Order;
import com.artemsolovev.model.School;
import com.artemsolovev.model.Student;
import com.artemsolovev.model.User;
import com.artemsolovev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Order>> add(@RequestBody Order order, @RequestParam long idStudent,
                                                     @RequestParam long idParent, @RequestParam long idSchool) {
        try {
            this.orderService.add(order, idStudent, idParent, idSchool);
            return new ResponseEntity<>(new ResponseResult<>(order, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Order>> get(@PathVariable long id) {
        try {
            Order order = this.orderService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(order, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Order>>> getActiveOrdersBySchool(@RequestParam LocalDate dateNow, @RequestParam long idSchool) {
        List<Order> list = this.orderService.getActiveOrdersBySchool(dateNow, idSchool);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @GetMapping(path = "/grade")
    public ResponseEntity<ResponseResult<List<Order>>> getActiveOrdersByGrade(@RequestParam LocalDate dateNow, @RequestParam String grade) {
        List<Order> list = this.orderService.getActiveOrdersByGrade(dateNow, grade);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @GetMapping(path = "/{idParent}")
    public ResponseEntity<ResponseResult<List<Order>>> getActiveOrdersByFamily(@PathVariable long idParent, @RequestParam LocalDate dateNow) {
        List<Order> list = this.orderService.getActiveOrdersByFamily(dateNow, idParent);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @GetMapping(path = "/family/{idParent}")
    public ResponseEntity<ResponseResult<List<Order>>> getByFamily(@PathVariable long idParent) {
        List<Order> list = this.orderService.getOrdersByFamily(idParent);
        return new ResponseEntity<>(new ResponseResult<>(list, null), HttpStatus.OK);
    }

    @PostMapping(path = "/addDish")
    public ResponseEntity<ResponseResult<Order>> addDish(@RequestParam long idOrder, @RequestParam long idDish) {
        try {
            Order order = this.orderService.addDish(idOrder, idDish);
            return new ResponseEntity<>(new ResponseResult<>(order, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
