package com.waw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	private Integer userId;
	private String userName;
	private String education;
	private String occupation;

}
