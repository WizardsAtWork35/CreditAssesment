package com.waw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.constant.Constant;
import com.waw.dto.FailedRuleDetailsDto;
import com.waw.dto.LoanRequestRuleDto;
import com.waw.entity.LoanRequest;
import com.waw.entity.LoanRequestRule;
import com.waw.exception.LoanRequestNotFoundException;
import com.waw.exception.LoanRequestRuleNotFoundException;
import com.waw.repository.LoanRequestRepository;
import com.waw.repository.LoanRequestRuleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanRequestRuleServiceImpl implements LoanRequestRuleService {

	@Autowired
	LoanRequestRuleRepository loanRequestRuleRepository;

	@Autowired
	LoanRequestRepository loanRequestRepository;

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
	public LoanRequestRuleDto getLoanStatus(Integer loanRequestId)
			throws LoanRequestNotFoundException, LoanRequestRuleNotFoundException {
		Optional<LoanRequest> loanRequest = loanRequestRepository.findById(loanRequestId);
		if (!loanRequest.isPresent()) {
			log.error("Exception occured in LoanRequestRuleServiceImpl getLoanStatus " +Constant.LOANREQUEST_NOT_FOUND);
			throw new LoanRequestNotFoundException(Constant.LOANREQUEST_NOT_FOUND);
		}
		List<LoanRequestRule> loanRequestRules = loanRequestRuleRepository.findByLoanRequest(loanRequest.get());
		if (loanRequestRules.isEmpty()) {
			log.error("Exception occured in LoanRequestRuleServiceImpl getLoanStatus " +Constant.LOANREQUESTRULE_NOT_FOUND);
			throw new LoanRequestRuleNotFoundException(Constant.LOANREQUESTRULE_NOT_FOUND);
		}
		log.info("Entering into LoanRequestRuleServiceImpl getLoanStatus: Analysing details ");
		List<FailedRuleDetailsDto> failedRuleDetailsDtos = new ArrayList<>();
		LoanRequestRuleDto loanRequestRuleDto = new LoanRequestRuleDto();
		loanRequestRules.forEach(loanRequestRule -> {
			if (loanRequestRule.getStatus().equals(Constant.FAILED)) {
				log.info("Entering into LoanRequestRuleServiceImpl getLoanStatus: fetching failed rules ");
				FailedRuleDetailsDto failedRuleDetailsDto = new FailedRuleDetailsDto();
				failedRuleDetailsDto.setRuleName(loanRequestRule.getRule().getRuleName());
				failedRuleDetailsDto.setStatus(loanRequestRule.getStatus());
				failedRuleDetailsDtos.add(failedRuleDetailsDto);
			}
		});
		if (failedRuleDetailsDtos.isEmpty()) {
			log.info("Entering into LoanRequestRuleServiceImpl getLoanStatus: approving loan ");
			loanRequestRuleDto.setMessage(Constant.LOAN_APPROVED);
			loanRequestRuleDto.setFailedRequestRules(failedRuleDetailsDtos);
		} else {
			log.info("Entering into LoanRequestRuleServiceImpl getLoanStatus: rejecting loan ");
			loanRequestRuleDto.setMessage(Constant.LOAN_REJECTED);
			loanRequestRuleDto.setFailedRequestRules(failedRuleDetailsDtos);
		}
		return loanRequestRuleDto;
	}

}
