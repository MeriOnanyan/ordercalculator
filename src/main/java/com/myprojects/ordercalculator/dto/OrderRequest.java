package com.myprojects.ordercalculator.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

    private String name;
    private String description;
    private LocalDate createDate;

    private List<LineItemRequest> lineItemRequests;
    
}
