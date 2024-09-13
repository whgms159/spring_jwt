package com.kosta.domain;

import com.kosta.entity.User;

import lombok.Data;

@Data
public class UserRequest {
	private Long id;
	private String email, password, name;
	
	public User toEntity() {
		return User.builder()
				.id(id)
				.email(email)
				.password(password)
				.name(name)
				.build();
	}
}
