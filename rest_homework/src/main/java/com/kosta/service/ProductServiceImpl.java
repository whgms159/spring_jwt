package com.kosta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.domain.ProductRequest;
import com.kosta.domain.ProductResponse;
import com.kosta.entity.Product;
import com.kosta.entity.User;
import com.kosta.repository.ProductRepository;
import com.kosta.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	@Override
	public ProductResponse insertProduct(ProductRequest pro) {
		User user = userRepository.findById(pro.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
		Product savedProduct = productRepository.save(pro.toEntity(user));
		return ProductResponse.toDTO(savedProduct);
	}
	
	@Override
	public List<ProductResponse> getAllProduct() {
		List<Product> proList = productRepository.findAll();
		List<ProductResponse> pList = proList.stream().map(ProductResponse::toDTO).toList();
		return pList;
	}



	@Override
	public ProductResponse updateProduct(ProductRequest pro) {
		Product prod = productRepository.findById(pro.getId()).orElseThrow(() -> new IllegalArgumentException("없삼"));
		if(prod.getName() != pro.getName()) prod.setName(pro.getName());
		if(prod.getPrice() != pro.getPrice()) prod.setPrice(pro.getPrice());
		Product updatedProd = productRepository.save(prod);
		return ProductResponse.toDTO(updatedProd);
	}

	@Override
	public ProductResponse deleteProduct(Long id) {
		Product prod = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없음"));
		productRepository.delete(prod);
		return ProductResponse.toDTO(prod);
	}



}
