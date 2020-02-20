package com.waw.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.waw.constant.Constant;
import com.waw.dto.FailedRuleDetailsDto;
import com.waw.dto.LoanRequestRuleDto;
import com.waw.entity.LoanRequest;
import com.waw.entity.LoanRequestRule;
import com.waw.entity.Rule;
import com.waw.exception.LoanRequestNotFoundException;
import com.waw.exception.LoanRequestRuleNotFoundException;
import com.waw.repository.LoanRequestRepository;
import com.waw.repository.LoanRequestRuleRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoanRequestRuleServiceTest {
	
	@InjectMocks
	LoanRequestRuleServiceImpl loanRequestRuleServiceImpl;
	
	@Mock
	LoanRequestRepository loanRequestRepository;
	
	@Mock
	LoanRequestRuleRepository loanRequestRuleRepository;
	
	Rule rule = new Rule();
	LoanRequest loanRequest = new LoanRequest();
	LoanRequestRule loanRequestRule = new LoanRequestRule();
	LoanRequestRuleDto loanRequestRuleDto = new LoanRequestRuleDto();
	List<LoanRequestRule> loanRequestRules = new ArrayList<>();
	FailedRuleDetailsDto failedRuleDetailsDto = new FailedRuleDetailsDto();
	List<FailedRuleDetailsDto> failedRuleDetailsDtos = new ArrayList<>();
	
	@Before
	public void init() {
		loanRequest.setLoanRequestId(1);
		loanRequestRule.setLoanRequestRuleId(1);
		loanRequestRule.setStatus(Constant.PASSED);
		loanRequestRule.setLoanRequest(loanRequest);
		loanRequestRules.add(loanRequestRule);
		rule.setRuleId(1);
		loanRequestRule.setRule(rule);
		loanRequestRuleDto.setMessage(Constant.LOAN_APPROVED);
		
	}
	
	@Test
	public void testGetLoanStatusForLoanApproved() throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		Mockito.when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loanRequest));
		Mockito.when(loanRequestRuleRepository.findByLoanRequest(loanRequest)).thenReturn(loanRequestRules);
		LoanRequestRuleDto actual = loanRequestRuleServiceImpl.getLoanStatus(1);
		assertEquals(Constant.LOAN_APPROVED, actual.getMessage());
	}
	
	@Test
	public void testGetLoanStatusForLoanRejected() throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		loanRequestRule.setStatus(Constant.FAILED);
		failedRuleDetailsDtos = new ArrayList<>();
		Mockito.when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loanRequest));
		Mockito.when(loanRequestRuleRepository.findByLoanRequest(loanRequest)).thenReturn(loanRequestRules);
		LoanRequestRuleDto actual = loanRequestRuleServiceImpl.getLoanStatus(1);
		assertEquals(Constant.LOAN_REJECTED, actual.getMessage());
	}
	
	@Test(expected = LoanRequestNotFoundException.class)
	public void testGetLoanStatusForLoanRequestNotFoundException() throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		Mockito.when(loanRequestRepository.findById(2)).thenReturn(Optional.of(loanRequest));
		loanRequestRuleServiceImpl.getLoanStatus(1);
	}
	
	@Test(expected = LoanRequestRuleNotFoundException.class)
	public void testGetLoanStatusForLoanRequestRuleNotFoundException() throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		loanRequestRules = new ArrayList<>();
		Mockito.when(loanRequestRepository.findById(1)).thenReturn(Optional.of(loanRequest));
		Mockito.when(loanRequestRuleRepository.findByLoanRequest(loanRequest)).thenReturn(loanRequestRules);
		loanRequestRuleServiceImpl.getLoanStatus(1);
	}


}
