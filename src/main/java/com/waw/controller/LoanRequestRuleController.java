package com.waw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waw.constant.Constant;
import com.waw.dto.LoanRequestRuleDto;
import com.waw.exception.LoanRequestNotFoundException;
import com.waw.exception.LoanRequestRuleNotFoundException;
import com.waw.service.LoanRequestRuleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loanrequests")
@CrossOrigin
@Slf4j
public class LoanRequestRuleController {
	
	@Autowired
	LoanRequestRuleService loanRequestRuleService;
	
	/**
	 * @author PriyaDharshini S
	 * @since 2020-02-20
	 * @param loanRequestId. Id of loan request raised.
	 * @return LoanRequestRuleDto. contains message and failed loans list.
	 * @throws LoanRequestNotFoundException     - thrown if the loanRequest is not
	 *                                          registered.
	 * @throws LoanRequestRuleNotFoundException - thrown when the loanRequestRule is
	 *                                          not created.
	 * 
	 */
	@GetMapping("/{loanRequestId}")
	public ResponseEntity<LoanRequestRuleDto> getLoanStatus(@PathVariable Integer loanRequestId) throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException{
		if(ObjectUtils.isEmpty(loanRequestId)) {
			log.error("Exception occured in LoanRequestRuleController getLoanStatus:"+Constant.LOANREQUEST_NOT_FOUND);
			throw new LoanRequestNotFoundException(Constant.LOANREQUEST_NOT_FOUND);
		}
		log.info("Entering into LoanRequestRuleController getLoanStatus: calling loanRequestRuleService");
		return new ResponseEntity<>(loanRequestRuleService.getLoanStatus(loanRequestId),HttpStatus.OK);
	}
	
	

}
