package com.cg.go.greatoutdoor.product.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.go.greatoutdoor.product.dao.IProductRepository;
import com.cg.go.greatoutdoor.product.entity.ProductEntity;
import com.cg.go.greatoutdoor.product.exception.ProductException;

@Transactional
@Service
public class ProductServiceImpl implements IProductService{
	
    private static final Logger Log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
    @Autowired
    IProductRepository daoProduct;

	@Override
	public List<ProductEntity> findAllProducts() {
		List<ProductEntity> list=daoProduct.findAll();
		return list;
	}

	@Override
	public ProductEntity findByProductId(Integer productId) {
		Optional<ProductEntity> optional=daoProduct.findById(productId);
		if(!optional.isPresent()){
            throw new ProductException("Product not found for id="+productId);
        }
		ProductEntity product=optional.get();
		return product;
	}

	@Override
	public List<ProductEntity> findByProductCategory(String productCategory) {
		List<ProductEntity> list=daoProduct.findByCategory(productCategory);
		return list;
	}

	@Override
	public ProductEntity addProduct(ProductEntity productEntity) {
		
		boolean exists=productEntity.getProductId()!=null && daoProduct.existsById(productEntity.getProductId());
        if(exists){
            throw new ProductException("Product already exists for id="+productEntity.getProductId());
        }
		ProductEntity product=daoProduct.save(productEntity);
		return product;
	}

	@Override
	public ProductEntity updateProduct(ProductEntity productEntity)  {
		boolean exists= daoProduct.existsById(productEntity.getProductId());
        if(!exists){
            throw new ProductException("Product does not exists for id="+productEntity.getProductId());
        }
		ProductEntity product=daoProduct.save(productEntity);
		return product;
	}


	@Override
	public void deleteByProductId(Integer productId) {
		Optional<ProductEntity> optional=daoProduct.findById(productId);
		if(!optional.isPresent()){
            throw new ProductException("Product not found for id="+productId);
        }
		daoProduct.deleteById(productId);
		
	}

	@Override
	public List<ProductEntity> search(String keyword) {
		List<ProductEntity> list=daoProduct.findByProductName(keyword);
		return list;
		
	}

	@Override
	public List<ProductEntity> filter(double maxPrice) {
		List<ProductEntity> list=daoProduct.findByRange(maxPrice);
		return list;
	}
	
	/*@Override
	public void updateProductQuantity(Integer quantity, Integer productId) {
		Optional<ProductEntity> optional=daoProduct.findById(productId);
		if(!optional.isPresent()){
            throw new ProductException("Product not found for id="+productId);
        }
		ProductEntity product=optional.get();
		product.setQuantity(quantity);
		
	}*/
}
