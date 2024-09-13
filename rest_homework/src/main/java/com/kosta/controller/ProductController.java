package com.kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.domain.ProductRequest;
import com.kosta.domain.ProductResponse;
import com.kosta.entity.User;
import com.kosta.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pro")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	//상품 추가
	@PostMapping("")
	public ResponseEntity<ProductResponse> insertProduct(ProductRequest pro){
		ProductResponse prod = productService.insertProduct(pro);
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}
	
	//상품 목록
	@GetMapping("")
	public ResponseEntity<List<ProductResponse>> getAllProduct(){
		List<ProductResponse> proList = new ArrayList<>();
		proList = productService.getAllProduct();
		return ResponseEntity.ok(proList);
	}
	//상품 디테일
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> productDetail(@PathVariable("id") Long id){
		return null;
	}
	
	//상품 수정
	@PatchMapping("")
	public ResponseEntity<ProductResponse> updateProduct(ProductRequest pro){
		ProductResponse prod = productService.updateProduct(pro);
		return ResponseEntity.ok(prod);
	}
	//상품 삭제 
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable("id") Long id){
		ProductResponse dpro = productService.deleteProduct(id);
		return ResponseEntity.ok(dpro);
	}

}
