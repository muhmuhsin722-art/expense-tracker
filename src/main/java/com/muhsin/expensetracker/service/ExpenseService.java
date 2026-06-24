package com.muhsin.expensetracker.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.muhsin.expensetracker.entity.Expense;
import com.muhsin.expensetracker.exception.ResourceNotFoundException;
import com.muhsin.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                    "Expense not found with id: " + id));
}
    

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense =
                expenseRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException(
                        		"Expense not found with id:" + id));

        if (existingExpense != null) {

            existingExpense.setName(expense.getName());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setExpensedate(expense.getExpensedate());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }
    

    public List<Expense> getExpensesByDate(LocalDate expenseDate){
		return expenseRepository.findByExpensedate(expenseDate);
    	
    }
    
    public Double getTotelExpeses() {
    	Double totel=expenseRepository.getTotelExpenses();
    	return totel != null ? totel : 0.0;
    }
    
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }
    
    public List<Expense> getExpenseBetweenDates(LocalDate startDate, LocalDate endDate){
    	return expenseRepository.findByExpensedateBetween(startDate ,endDate);
    	
    }
}