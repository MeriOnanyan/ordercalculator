package com.myprojects.oredercalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.oredercalculator.model.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {


    
}
