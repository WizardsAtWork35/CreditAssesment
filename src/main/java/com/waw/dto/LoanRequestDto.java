package com.waw.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
	private String userName;
	private Long mobile;
	private String bankRelationType;
	private String email;
	private String residenceCity;
	private Double LoanAmount;
	private String LoanType;
	private Double Salary;
	private String assertName;
	private Double assertValue;
}
