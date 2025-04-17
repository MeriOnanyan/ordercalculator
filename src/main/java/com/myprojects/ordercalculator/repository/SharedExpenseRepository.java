package com.myprojects.ordercalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.ordercalculator.model.SharedExpense;

@Repository
public interface SharedExpenseRepository extends JpaRepository<SharedExpense, Long> {

}
