package com.waw.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.waw.dto.LoanRequestDto;
import com.waw.repository.LoanRequestRepository;
import com.waw.repository.LoanRequestRuleRepository;
import com.waw.repository.RuleRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoanRequestServiceTest {

	@InjectMocks
	LoanRequestServiceImpl loanRequestServiceImpl;

	@Mock
	LoanRequestRepository loanRequestRepository;

	@Mock
	LoanRequestRuleRepository loanRequestRuleRepository;

	@Mock
	RuleRepository ruleRepository;
	
	@Before
	public void setup() {
		LoanRequestDto loanRequestDto = new LoanRequestDto();
	}
}
