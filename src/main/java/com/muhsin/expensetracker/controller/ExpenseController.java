package com.muhsin.expensetracker.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.muhsin.expensetracker.entity.Expense;
import com.muhsin.expensetracker.exception.ResourceNotFoundException;
import com.muhsin.expensetracker.repository.ExpenseRepository;
import com.muhsin.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
	
	@Autowired
	private ExpenseService expenseService;

    ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
	
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
//	
	 @GetMapping("/category/{category}")
	 public List<Expense> getExpensesByCategory(
	         @PathVariable String category) {

	     return expenseService.getExpensesByCategory(category);
	 }
	 @GetMapping("/date/{expenseDate}")
	 public List<Expense> getExpensesByDate(@PathVariable  LocalDate expenseDate){
		 return expenseService.getExpensesByDate(expenseDate);
	 }
	 
	 @GetMapping("/total")
	 public Double getTotelExpenses() {
		 return expenseRepository.getTotelExpenses();
	 }
	 
	 @GetMapping("/page")
	 public Page<Expense> getExpenseWithPagination(
			 @RequestParam int page,
			 @RequestParam int size){
		 Pageable pageable=PageRequest.of(page, size,Sort.by("amount").descending());
		 return expenseService.getAllExpenses(pageable);
	 }
	 
	 @GetMapping("/between/{startDate}/{endDate}")
	 public List<Expense> getExpenseBetweenDates(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
		 return expenseService.getExpenseBetweenDates(startDate, endDate);
	 }
	 }

	 



