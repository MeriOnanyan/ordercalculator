package com.myprojects.ordercalculator.controller;

import java.time.LocalDate;
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

import com.myprojects.ordercalculator.dto.OrderRequest;
import com.myprojects.ordercalculator.dto.OrderUpdateRequest;
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
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest dto) {
        Order created = orderService.createOrder(dto);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateRequest dto) {
        Order updated = orderService.updateOrder(orderId, dto); 
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
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
