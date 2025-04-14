package com.myprojects.ordercalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.ordercalculator.dto.OrderUpdateRequest;
import com.myprojects.ordercalculator.model.Order;
import com.myprojects.ordercalculator.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order updateOrder(Long orderId, OrderUpdateRequest orderUpdate) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setName(orderUpdate.getName());
        order.setDescription(orderUpdate.getDescription());

        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }

}
