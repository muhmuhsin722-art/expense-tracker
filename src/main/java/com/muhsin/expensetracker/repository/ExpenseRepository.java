package com.muhsin.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.muhsin.expensetracker.entity.Expense;
import java.time.LocalDate;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByCategory(String category);
	
	List<Expense> findByExpensedate(LocalDate expensedate);
	
	@Query("SELECT SUM(e.amount) FROM Expense e")Double getTotelExpenses();
	
	List<Expense> findByExpensedateBetween(
	        LocalDate startDate,
	        LocalDate endDate);
}
