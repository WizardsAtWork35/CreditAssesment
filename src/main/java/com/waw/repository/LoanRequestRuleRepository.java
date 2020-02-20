package com.waw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.entity.LoanRequest;
import com.waw.entity.LoanRequestRule;

@Repository
public interface LoanRequestRuleRepository extends JpaRepository<LoanRequestRule, Integer> {

	List<LoanRequestRule> findByLoanRequest(LoanRequest loanRequest);

}
