package com.waw.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.waw.constant.Constant;
import com.waw.dto.FailedRuleDetailsDto;
import com.waw.dto.LoanRequestDto;
import com.waw.dto.LoanRequestRuleDto;
import com.waw.dto.LoanResponseDto;
import com.waw.entity.LoanRequest;
import com.waw.entity.LoanRequestRule;
import com.waw.entity.Rule;
import com.waw.exception.RulesNotFoundException;
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
	
		Rule rule = new Rule();
		LoanRequest loanRequest = new LoanRequest();
		LoanRequestRule loanRequestRule = new LoanRequestRule();
		List<Rule> rules = new ArrayList<>();
		LoanRequestDto loanRequestDto = new LoanRequestDto();
		LoanResponseDto loanResponseDto = new LoanResponseDto();
		@Before
		public void init() {
			loanRequest.setLoanRequestId(1);
			loanRequestRule.setLoanRequestRuleId(1);
			loanRequestRule.setStatus(Constant.PASSED);
			loanRequestRule.setLoanRequest(loanRequest);
			rule.setRuleId(1);
			loanRequestRule.setRule(rule);
			loanRequestDto.setAssertName("Car");
			loanRequestDto.setAssertValue(2009.0);
			rules.add(rule);
			loanResponseDto.setMessage(Constant.SUCESSES_MESSAGE);
		}
		@Test
	public void testLoanRequest() throws RulesNotFoundException {
			Mockito.when(loanRequestRepository.save(loanRequest)).thenReturn(loanRequest);
			Mockito.when(ruleRepository.findAll()).thenReturn(rules);
			Mockito.when(loanRequestRuleRepository.save(loanRequestRule)).thenReturn(loanRequestRule);
			LoanResponseDto actual = loanRequestServiceImpl.requestForLoan(loanRequestDto);
			assertEquals(Constant.SUCESSES_MESSAGE, actual.getMessage());
		}

		
}
