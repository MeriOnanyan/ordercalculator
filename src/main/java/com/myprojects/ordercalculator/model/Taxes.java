package com.myprojects.ordercalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
@Entity
public class Taxes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taxesType;
    private double percentage;
    private double moneyAmount;
        
}
