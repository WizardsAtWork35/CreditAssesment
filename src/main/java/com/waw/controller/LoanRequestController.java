package com.waw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waw.dto.LoanRequestDto;
import com.waw.dto.LoanResponseDto;
import com.waw.exception.RulesNotFoundException;
import com.waw.service.LoanRequestService;

/**
 * 
 * @author Yoga
 *
 */
@RestController
@RequestMapping("/loan")
public class LoanRequestController {
	private static final Logger logger = LoggerFactory.getLogger(LoanRequestController.class);
	@Autowired
	LoanRequestService loanRequestService;
	
	@PostMapping
	public ResponseEntity<LoanResponseDto> requestForLoan(@RequestBody LoanRequestDto loanRequestDto)
			throws RulesNotFoundException{
		logger.info("Entering into requestForLoan method in LoanRequestController");
		return ResponseEntity.ok().body(loanRequestService.requestForLoan(loanRequestDto));
	}
}
