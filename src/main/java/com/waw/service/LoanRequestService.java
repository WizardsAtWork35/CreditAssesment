package com.waw.service;

import com.waw.dto.LoanRequestDto;
import com.waw.dto.LoanResponseDto;
import com.waw.exception.RulesNotFoundException;

public interface LoanRequestService {

	public LoanResponseDto requestForLoan(LoanRequestDto loanRequestDto) throws RulesNotFoundException;

}
