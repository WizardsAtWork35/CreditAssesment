package com.waw.dto;

import lombok.Data;

@Data
public class LoanResponseDto {
	private String message;
	private Integer loanId;
	private Integer status;
}
