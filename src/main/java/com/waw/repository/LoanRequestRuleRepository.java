package com.waw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.entity.LoanRequestRule;

@Repository
public interface LoanRequestRuleRepository extends JpaRepository<LoanRequestRule, Integer> {

}
