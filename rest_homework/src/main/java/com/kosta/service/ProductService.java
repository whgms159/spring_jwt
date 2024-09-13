package com.kosta.service;

import java.util.List;

import com.kosta.domain.ProductRequest;
import com.kosta.domain.ProductResponse;
import com.kosta.entity.User;

public interface ProductService {

	List<ProductResponse> getAllProduct();



	ProductResponse updateProduct(ProductRequest pro);

	ProductResponse deleteProduct(Long id);

	ProductResponse insertProduct(ProductRequest pro);

}
