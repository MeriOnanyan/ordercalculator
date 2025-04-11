package com.myprojects.ordercalculator.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.ordercalculator.dto.LineItemRequest;
import com.myprojects.ordercalculator.dto.OrderRequest;
import com.myprojects.ordercalculator.dto.OrderUpdate;
import com.myprojects.ordercalculator.model.LineItem;
import com.myprojects.ordercalculator.model.Order;
import com.myprojects.ordercalculator.repository.OrderRepository;
import com.myprojects.ordercalculator.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/latest")
    public Order getLatestOrder() {
        List<Order> orders = orderRepository.findAll();
    
        return orders.isEmpty() ? null : orders.get(orders.size() - 1);
    }

    @PostMapping("/create")
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
    
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderRequest> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdate dto) {
        OrderRequest updated = orderService.updateOrder(orderId, dto); 
        return ResponseEntity.ok(updated);
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
