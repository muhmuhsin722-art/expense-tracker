package com.muhsin.expensetracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor




@Entity
@Table(name= "expenses")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message= "expense name cannot be empty")
	private String name;
	
	
	
	@Positive(message="Amount must be greater than zero")
	private Double amount;
	
	
	@NotBlank(message="catogory cannot be empty")
	private String category;
	
	private LocalDate expensedate;
//	public String getName() {
//	    return name;
//	}
//
//	public void setName(String name) {
//	    this.name = name;
//	}
}
