package com.kosta.domain;

import com.kosta.entity.Product;
import com.kosta.entity.User;

import lombok.Data;

@Data
public class ProductRequest {
	private Long id;
	private String name;
	private Long price;
	private Long userId;
	
	public Product toEntity(User user) {
		return Product.builder()
				.id(id)
				.user(user)
				.name(name)
				.price(price)
				.build();
	}
}
