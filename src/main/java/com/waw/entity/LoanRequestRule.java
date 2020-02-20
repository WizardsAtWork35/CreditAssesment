package com.waw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class LoanRequestRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanRequestRuleId;
	@ManyToOne
	@JoinColumn(name = "loanrequest_id")
	private LoanRequest loanRequest;
	@ManyToOne
	@JoinColumn(name = "rule_id")
	private Rule rule;
	private String status;
	

}