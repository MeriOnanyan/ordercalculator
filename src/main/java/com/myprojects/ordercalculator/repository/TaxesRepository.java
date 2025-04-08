package com.myprojects.ordercalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myprojects.ordercalculator.model.Taxes;

public interface TaxesRepository extends JpaRepository<Taxes, Long> {

    
} 
