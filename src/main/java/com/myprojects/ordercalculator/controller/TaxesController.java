package com.myprojects.ordercalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.ordercalculator.model.Taxes;
import com.myprojects.ordercalculator.repository.TaxesRepository;


@RestController
public class TaxesController {
    
    @Autowired
    private TaxesRepository taxesRepository;

    @GetMapping("/taxes")
    public Taxes sampleTaxes() {
 
        Taxes taxes = new Taxes();

        taxes.setTaxesType("");
        taxes.setPercentage(0);
        taxes.setMoneyAmount(0);

        return taxesRepository.save(taxes);
    }
    
}

