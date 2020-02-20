package com.waw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LoanRequest {
	@Id
	private Integer loanRequestId;
	private String userName;
	private String education;
	private String occupation;
	private String email;
	private String phone;
}
