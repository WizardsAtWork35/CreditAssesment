package com.waw.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.constant.Constant;
import com.waw.dto.LoanRequestDto;
import com.waw.dto.LoanResponseDto;
import com.waw.entity.LoanRequest;
import com.waw.entity.LoanRequestRule;
import com.waw.entity.Rule;
import com.waw.exception.RulesNotFoundException;
import com.waw.repository.LoanRequestRepository;
import com.waw.repository.LoanRequestRuleRepository;
import com.waw.repository.RuleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanRequestServiceImpl implements LoanRequestService {

	@Autowired
	LoanRequestRepository loanRequestRepository;
	@Autowired
	RuleRepository ruleRepository;
	@Autowired
	LoanRequestRuleRepository LoanRequestRuleRepository;

	/**
	 * @author hackathon @
	 * @throws RulesNotFoundException
	 */
	@Override
	public LoanResponseDto requestForLoan(LoanRequestDto loanRequestDto) throws RulesNotFoundException {
		log.info("Enter into requestForLoan meethond in LoanRequestServiceImpl");
		LoanRequest loanRequest = new LoanRequest();
		BeanUtils.copyProperties(loanRequestDto, loanRequest);
		loanRequest.setUserName(loanRequestDto.getUserName());
		loanRequest.setBankRelationType(loanRequestDto.getBankRelationType());
		loanRequest.setEmail(loanRequestDto.getEmail());
		loanRequest.setLoanAmount(loanRequestDto.getLoanAmount());
		loanRequest.setMobile(loanRequestDto.getMobile());
		loanRequest.setSalary(loanRequestDto.getSalary());
		loanRequest.setLoanType(loanRequestDto.getLoanType());
		loanRequest.setResidenceCity(loanRequestDto.getResidenceCity());
		loanRequest.setAssertName(loanRequestDto.getAssertName());
		loanRequest.setAssertValue(loanRequestDto.getAssertValue());
		loanRequestRepository.save(loanRequest);
		List<Rule> rules = ruleRepository.findAll();
		if(rules.isEmpty()) {
			throw new RulesNotFoundException(Constant.RULES_NOT_FOUND);
		}
		rules.forEach(rule -> {
			LoanRequestRule loanRequestRule = new LoanRequestRule();
			loanRequestRule.setLoanRequest(loanRequest);
			loanRequestRule.setRule(rule);
			loanRequestRule.setStatus(Constant.STATUS);
			LoanRequestRuleRepository.save(loanRequestRule);
		});
		LoanResponseDto loanResponseDto = new LoanResponseDto();
		loanResponseDto.setMessage(Constant.SUCESSES_MESSAGE);
		loanResponseDto.setStatus(Constant.STATUS_CODE);
		loanResponseDto.setLoanId(loanRequest.getLoanId());

		return loanResponseDto;
	}

}
