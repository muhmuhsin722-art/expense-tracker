package com.muhsin.expensetracker.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
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

import com.muhsin.expensetracker.dto.ExpenseRequestDto;
import com.muhsin.expensetracker.dto.ExpenseResponseDto;
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
	@Operation(summary="create new expense")
	@PostMapping
	public ExpenseResponseDto saveExpense(
	        @Valid @RequestBody ExpenseRequestDto requestDto) {

	    Expense expense = new Expense();

	    expense.setName(requestDto.getName());
	    expense.setAmount(requestDto.getAmount());
	    expense.setCategory(requestDto.getCategory());
	    expense.setExpensedate(requestDto.getExpensedate());

	    Expense savedExpense = expenseService.saveExpense(expense);

	    ExpenseResponseDto responseDto = new ExpenseResponseDto();

	    responseDto.setId(savedExpense.getId());
	    responseDto.setName(savedExpense.getName());
	    responseDto.setAmount(savedExpense.getAmount());
	    responseDto.setCategory(savedExpense.getCategory());
	    responseDto.setExpensedate(savedExpense.getExpensedate());

	    return responseDto;
	}
	@Operation(summary = "Get all expenses")
	@GetMapping
	public List<Expense> getAllExpense(){
		return expenseService.getAllExpenses();
	}
	@Operation(summary = "Get expenses by Id")
	 @GetMapping("/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
	    return expenseService.getExpenseById(id);
	}
	@Operation(summary = "update expense")
	 @PutMapping("/{id}")
	 public Expense updateExpense(
			 @PathVariable   Long id,
			 @Valid@RequestBody   Expense expense) {
		 return expenseService.updateExpense(id, expense);
	 }
	@Operation(summary = "Delete expenses")
	 @DeleteMapping("/{id}")
	 public void deleteExpense(@PathVariable Long id) {
	     expenseService.deleteExpense(id);
	 }
	
	
	@Operation(summary = "Get expenses By Catogory")
	 @GetMapping("/category/{category}")
	 public List<Expense> getExpensesByCategory(
	         @PathVariable String category) {

	     return expenseService.getExpensesByCategory(category);
	 }
	
	@Operation(summary = "Get all expenses By date")
	 @GetMapping("/date/{expenseDate}")
	 public List<Expense> getExpensesByDate(@PathVariable  LocalDate expenseDate){
		 return expenseService.getExpensesByDate(expenseDate);
	 }
	@Operation(summary = "Get total expenses")
	 @GetMapping("/total")
	 public Double getTotelExpenses() {
		 return expenseRepository.getTotelExpenses();
	 }
	@Operation(summary = "Get expenses with pagination and sorting")
	 @GetMapping("/page")
	 public Page<Expense> getExpenseWithPagination(
			 @RequestParam int page,
			 @RequestParam int size){
		 Pageable pageable=PageRequest.of(page, size,Sort.by("amount").descending());
		 return expenseService.getAllExpenses(pageable);
	 }
	 @Operation(summary = "Get all expenses betwee cirtian date")
	 @GetMapping("/between/{startDate}/{endDate}")
	 public List<Expense> getExpenseBetweenDates(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
		 return expenseService.getExpenseBetweenDates(startDate, endDate);
	 }
	 
	 }

	 



