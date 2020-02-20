package com.waw.dto;

import java.util.List;

import lombok.Data;

@Data
public class LoanRequestRuleDto {

	private String message;
	private List<FailedRuleDetailsDto> failedRequestRules;

}
