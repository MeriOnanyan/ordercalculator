package com.myprojects.ordercalculator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.myprojects.ordercalculator.dto.LineItemRequest;
import com.myprojects.ordercalculator.dto.OrderRequest;
import com.myprojects.ordercalculator.dto.OrderUpdateRequest;
import com.myprojects.ordercalculator.model.LineItem;
import com.myprojects.ordercalculator.model.Order;
import com.myprojects.ordercalculator.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        Order orderItem = new Order();

        orderItem.setName(orderRequest.getName());
        orderItem.setDescription(orderRequest.getDescription());
        orderItem.setCreateDate(orderRequest.getCreateDate());

        List<LineItemRequest> lineItemRequests = orderRequest.getLineItemRequests();
        List<LineItem> lineItems = new ArrayList<>();

        if (lineItemRequests != null){
            for (int i = 0; i < lineItemRequests.size(); i++) {
                LineItemRequest lineItemRequestOne = lineItemRequests.get(i);
                LineItem lineItemOne = new LineItem();
    
                lineItemOne.setName(lineItemRequestOne.getName());
                lineItemOne.setPrice(lineItemRequestOne.getPrice());
                lineItemOne.setWeight(lineItemRequestOne.getWeight());
                lineItemOne.setCustomsDuty(lineItemRequestOne.getCustomsDuty());
                lineItemOne.setOrder(orderItem);
    
                lineItems.add(lineItemOne);
            }
    
            orderItem.setLineItems(lineItems);
    
        }

        return orderRepository.save(orderItem);
    }

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
