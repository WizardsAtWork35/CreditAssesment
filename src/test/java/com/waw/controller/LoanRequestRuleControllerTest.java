package com.waw.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.waw.dto.LoanRequestRuleDto;
import com.waw.exception.LoanRequestNotFoundException;
import com.waw.exception.LoanRequestRuleNotFoundException;
import com.waw.service.LoanRequestRuleService;

@RunWith(MockitoJUnitRunner.class)
public class LoanRequestRuleControllerTest {
	
	@InjectMocks
	LoanRequestRuleController loanRequestRuleController;
	
	@Mock
	LoanRequestRuleService loanRequestRuleService;
	
	@Test
	public void testGetLoanStatus() throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		Mockito.when(loanRequestRuleService.getLoanStatus(1)).thenReturn(new LoanRequestRuleDto());
		ResponseEntity<LoanRequestRuleDto> actual = loanRequestRuleController.getLoanStatus(1);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
