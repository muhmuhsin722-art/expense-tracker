package com.muhsin.expensetracker.dto;

import java.time.LocalDate;
import lombok.Data;
@Data
public class ExpenseResponseDto {

    private Long id;
    private String name;
    private Double amount;
    private String category;
    private LocalDate expensedate;
}

