package com.cg.go.greatoutdoor.product.service;

import java.util.List;

import com.cg.go.greatoutdoor.product.entity.ProductEntity;

public interface IProductService {

	List<ProductEntity> findAllProducts();

	List<ProductEntity> findByProductCategory(String productCategory);

	ProductEntity addProduct(ProductEntity productEntity);

	ProductEntity updateProduct(ProductEntity productEntity);

	List<ProductEntity> search(String keyword);

	List<ProductEntity> filter(double maxPrice);

	ProductEntity findByProductId(Integer productId);

	void deleteByProductId(Integer productId);

}
