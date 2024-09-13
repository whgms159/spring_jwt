package com.kosta.domain;

import com.kosta.entity.Product;
import com.kosta.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
	private Long id;
	private String name;
	private Long price;
	private String userName;
	
	public static ProductResponse toDTO(Product pro) {
		return ProductResponse.builder()
				.id(pro.getId())
				.name(pro.getName())
				.price(pro.getPrice())
				.userName(pro.getUser().getName())
				.build();
	}
}
