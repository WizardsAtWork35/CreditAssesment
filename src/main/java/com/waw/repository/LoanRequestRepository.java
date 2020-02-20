package com.waw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.entity.LoanRequest;
@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer>{

}
