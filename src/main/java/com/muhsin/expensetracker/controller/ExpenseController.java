package com.muhsin.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muhsin.expensetracker.entity.Expense;
import com.muhsin.expensetracker.exception.ResourceNotFoundException;
import com.muhsin.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping
	public Expense saveExpense(@Valid @RequestBody Expense expense) {
		
		   System.out.println(expense.getName());
		    System.out.println(expense.getAmount());
		    System.out.println(expense.getCategory());
		    System.out.println(expense.getExpensedate());
		    
		return expenseService.saveExpense(expense);
	}
	
	@GetMapping
	public List<Expense> getAllExpense(){
		return expenseService.getAllExpenses();
	}
	
	 @GetMapping("/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
	    return expenseService.getExpenseById(id);
	}
	 
	 @PutMapping("/{id}")
	 public Expense updateExpense(
			 @PathVariable   Long id,
			 @Valid@RequestBody   Expense expense) {
		 return expenseService.updateExpense(id, expense);
	 }
	 
	 @DeleteMapping("/{id}")
	 public void deleteExpense(@PathVariable Long id) {
	     expenseService.deleteExpense(id);
	 }
	 @GetMapping("/test")
	 public String test() {
	     throw new ResourceNotFoundException("Test exception");
	 }
	 
}


