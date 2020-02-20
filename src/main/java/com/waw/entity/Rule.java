package com.waw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Rule {

	@Id
	private Integer ruleId;
	private String ruleName;
	private Double threshold;

}