package com.waw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LoanRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanRequestId;
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