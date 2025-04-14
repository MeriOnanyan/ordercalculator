package com.myprojects.ordercalculator.dto;

import lombok.Data;

@Data
public class LineItemRequest {

    private String name;
    private double price;
    private double weight;
    private double customsDuty;
    
}
