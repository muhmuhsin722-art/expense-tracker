package com.muhsin.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muhsin.expensetracker.entity.Expense;
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
                .orElse(null);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense =
                expenseRepository.findById(id)
                        .orElse(null);

        if (existingExpense != null) {

            existingExpense.setName(expense.getName());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setExpensedate(expense.getExpensedate());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }
}