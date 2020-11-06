package com.cg.go.greatoutdoor.product.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.go.greatoutdoor.product.entity.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Integer>{

	
	@Query("from ProductEntity where price<=:maxPrice")
	List<ProductEntity> findByRange(@Param("maxPrice") double maxPrice);
	
	List<ProductEntity> findByCategory(String productCategory);

	List<ProductEntity> findByProductName(String keyword);
	
}
