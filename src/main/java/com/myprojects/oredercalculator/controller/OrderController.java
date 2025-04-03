package com.myprojects.oredercalculator.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.oredercalculator.model.Order;
import com.myprojects.oredercalculator.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    // private OrderRepository orderRepository;

    @GetMapping("/latest")
    public Order getLatestOrder() {
        List<Order> orders = orderRepository.findAll();

        int lastIndex = orders.size() - 1;
        return orders.get(lastIndex);
    }

    @GetMapping("/sample")
    public Order sampleOrder() {
        Order orderItem = new Order();  

        // orderItem.setId(1L);
        orderItem.setName("quitar");
        orderItem.setDescription("red and big");
        orderItem.setCreateDate(LocalDate.now());

        return orderRepository.save(orderItem);
    } 

}
