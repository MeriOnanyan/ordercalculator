package com.myprojects.ordercalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.myprojects.ordercalculator.dto.OrderRequest;
// import com.myprojects.ordercalculator.dto.OrderUpdate;
import com.myprojects.ordercalculator.model.Order;
import com.myprojects.ordercalculator.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // @Transactional
    // public OrderRequest updateOrder(Long orderId, OrderUpdate orderUpdateDTO) {
    //     Order order = orderRepository.findById(orderId)
    //             .orElseThrow(() -> new RuntimeException("Order not found"));

    //     order.setName(orderUpdate.getName());
    //     order.setDescription(orderUpdate.getDescription());

    //     Order updatedOrder = orderRepository.save(order);

    //     return new OrderRequest(updatedOrder);
    // }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
}
}
