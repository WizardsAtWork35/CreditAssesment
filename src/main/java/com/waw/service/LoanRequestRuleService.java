package com.waw.service;

import com.waw.dto.LoanRequestRuleDto;
import com.waw.exception.LoanRequestNotFoundException;
import com.waw.exception.LoanRequestRuleNotFoundException;

public interface LoanRequestRuleService {
	
	LoanRequestRuleDto getLoanStatus(Integer loanRequestId) throws LoanRequestNotFoundException,LoanRequestRuleNotFoundException;

}
