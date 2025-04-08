package com.myprojects.ordercalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.ordercalculator.model.LineItem;
import com.myprojects.ordercalculator.repository.LineItemRepository;


@RestController
public class LineItemController {
    
        @Autowired
        private LineItemRepository lineItemRepository;

        @GetMapping("/lineItem")
        public LineItem sampleLineItem() {

            LineItem lineItem = new LineItem();

            lineItem.setName("");
            lineItem.setPrice(50);
            lineItem.setWeight(25);
            lineItem.setCustomsDuty(40);

            return lineItemRepository.save(lineItem);
        
    }

}
