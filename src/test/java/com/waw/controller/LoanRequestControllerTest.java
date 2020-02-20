package com.waw.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.waw.constant.Constant;
import com.waw.dto.LoanRequestDto;
import com.waw.dto.LoanResponseDto;
import com.waw.exception.RulesNotFoundException;
import com.waw.service.LoanRequestService;

@RunWith(MockitoJUnitRunner.class)
public class LoanRequestControllerTest {

	@Mock
	LoanRequestService loanRequestService;

	@InjectMocks
	LoanRequestController loanRequestController;

	LoanRequestDto loanRequestDto = null;
	LoanResponseDto loanResponseDto = null;
	@Before
	public void setup() {
		loanRequestDto = new LoanRequestDto();
		loanResponseDto = new LoanResponseDto();
		loanRequestDto.setAssertName("Car");
		loanRequestDto.setAssertValue(2009.0);
		loanRequestDto.setBankRelationType("Savings");
		loanRequestDto.setEmail("yoga@gmail.com");
		loanRequestDto.setMobile(9491777925L);
		loanRequestDto.setResidenceCity("Bangalore");
		loanRequestDto.setSalary(8907.9);
		loanRequestDto.setLoanAmount(23458.98);
		loanRequestDto.setLoanType("Personal");
		loanResponseDto.setMessage(Constant.SUCESSES_MESSAGE);
	}
	
	@Test
	public void loanRequestTest() throws RulesNotFoundException {
		Mockito.when(loanRequestService.requestForLoan(loanRequestDto)).thenReturn(loanResponseDto);
		ResponseEntity<LoanResponseDto> actual = loanRequestController.requestForLoan(loanRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
}
