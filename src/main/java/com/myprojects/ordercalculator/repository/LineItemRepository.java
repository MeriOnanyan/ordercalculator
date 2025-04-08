package com.myprojects.ordercalculator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.ordercalculator.model.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}
