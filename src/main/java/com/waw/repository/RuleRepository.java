package com.waw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.entity.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer>{

}
