package com.myprojects.ordercalculator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.ordercalculator.dto.OrderUpdateRequest;
import com.myprojects.ordercalculator.dto.SharedExpenseRequest;
import com.myprojects.ordercalculator.model.Order;
import com.myprojects.ordercalculator.model.SharedExpense;
import com.myprojects.ordercalculator.repository.OrderRepository;
import com.myprojects.ordercalculator.repository.SharedExpenseRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private SharedExpenseRepository sharedExpenseRepository;

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

    public void setSharedExpenses(Long orderId, List<SharedExpenseRequest> expenseRequests) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    List<SharedExpense> expenses = expenseRequests.stream().map(req -> {
        SharedExpense expense = new SharedExpense();
        expense.setName(req.getName());
        expense.setAmount(req.getAmount());
        expense.setOrder(order);
        return expense;
    }).collect(Collectors.toList());

    sharedExpenseRepository.saveAll(expenses);
}

}
