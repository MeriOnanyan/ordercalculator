package com.myprojects.ordercalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.ordercalculator.dto.LineItemRequest;
import com.myprojects.ordercalculator.model.LineItem;
import com.myprojects.ordercalculator.repository.LineItemRepository;
import com.myprojects.ordercalculator.repository.OrderRepository;


@RestController
@RequestMapping("/api/line-items")
public class LineItemController {
    
        @Autowired
        private LineItemRepository lineItemRepository;

        @Autowired 
        private OrderRepository orderRepository;

        @GetMapping("/lineItem")
        public LineItem sampleLineItem() {

            LineItem lineItem = new LineItem();

            lineItem.setName("");
            lineItem.setPrice(50);
            lineItem.setWeight(25);
            lineItem.setCustomsDuty(40);

            return lineItemRepository.save(lineItem);
        
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<?> addLineItemToOrder(@PathVariable Long orderId, @RequestBody LineItemRequest request) {
        return orderRepository.findById(orderId).map(order -> {
            LineItem lineItem = new LineItem();
            lineItem.setName(request.getName());
            lineItem.setPrice(request.getPrice());
            lineItem.setWeight(request.getWeight());
            lineItem.setCustomsDuty(request.getCustomsDuty());
            lineItem.setOrder(order); 

            lineItemRepository.save(lineItem); 

            return ResponseEntity.ok("LineItem added to Order " + orderId);
        }).orElse(ResponseEntity.notFound().build());
    }
}
