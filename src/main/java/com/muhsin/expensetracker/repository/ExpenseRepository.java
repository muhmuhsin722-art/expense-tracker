package com.muhsin.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muhsin.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
