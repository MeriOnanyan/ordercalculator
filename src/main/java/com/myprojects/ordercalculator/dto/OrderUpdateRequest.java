package com.myprojects.ordercalculator.dto;

import lombok.Data;

@Data
public class OrderUpdateRequest {
    private String name;
    private String description;

}