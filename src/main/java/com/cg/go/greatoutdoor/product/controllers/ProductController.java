package com.cg.go.greatoutdoor.product.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.go.greatoutdoor.product.dto.CreateProductRequest;
import com.cg.go.greatoutdoor.product.dto.ProductDetails;
import com.cg.go.greatoutdoor.product.dto.UpdateProductRequest;
import com.cg.go.greatoutdoor.product.entity.ProductEntity;
import com.cg.go.greatoutdoor.product.service.IProductService;
import com.cg.go.greatoutdoor.product.service.ProductServiceImpl;

@RequestMapping("/productstable")
@RestController
public class ProductController {
	
	 private static final Logger Log = LoggerFactory.getLogger(ProductController.class);
		
	@Autowired
	public IProductService productService;
	
	/**
     * effective url will be http://localhost:8585/productstable/add
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ProductDetails add(@RequestBody CreateProductRequest requestData) {
        ProductEntity product = new ProductEntity(requestData.getProductName(), requestData.getPrice(), requestData.getImage(),requestData.getColor(),
        		requestData.getCategory(),requestData.getQuantity(),requestData.getManufacturer(),requestData.getSpecification());
        product = productService.addProduct(product);
        ProductDetails details = toDetails(product);
        return details;
    }

	@PutMapping("/update")
    public ProductDetails update(@RequestBody UpdateProductRequest requestData) {
		ProductEntity product = new ProductEntity(requestData.getProductName(), requestData.getPrice(), requestData.getImage(),requestData.getColor(),
        		requestData.getCategory(),requestData.getQuantity(),requestData.getManufacturer(),requestData.getSpecification());
		product.setProductId(requestData.getProductId());
		product = productService.updateProduct(product);
        ProductDetails details = toDetails(product);
        return details;
    }


    @GetMapping("/by/productid/{id}")
    public ProductDetails findProduct(@PathVariable("id") Integer productId) {
        ProductEntity product = productService.findByProductId(productId);
        ProductDetails details = toDetails(product);
        return details;
    }
    
    @GetMapping("/allProducts")
    public List<ProductDetails> findAll() {
        List<ProductEntity> product = productService.findAllProducts();
        List<ProductDetails> details = toDetails(product);
        return details;
    }
    
    @GetMapping("/by/productcategory/{category}")
    public List<ProductDetails> findByCategory(@PathVariable("category") String category) {
        List<ProductEntity> product = productService.findByProductCategory(category);
        List<ProductDetails> details = toDetails(product);
        return details;
    }
    
    @GetMapping("/by/productname/{name}")
    public List<ProductDetails> findProduct(@PathVariable("name") String productName) {
        List<ProductEntity> product = productService.search(productName);
        List<ProductDetails> details = toDetails(product);
        return details;
    }
    
    @GetMapping("/by/maxprice/{price}")
    public List<ProductDetails> filterProduct(@PathVariable("price") double maxPrice) {
        List<ProductEntity> product = productService.filter(maxPrice);
        List<ProductDetails> details = toDetails(product);
        return details;
    }
    
    @DeleteMapping("/remove/{id}")
    public String deleteProduct(@PathVariable("id") Integer productId) {
        productService.deleteByProductId(productId);
        String response = "removed product with id=" + productId;
        return response;
    }
    
    
	private ProductDetails toDetails(ProductEntity product) {
		
		ProductDetails productDetails = new ProductDetails(product.getProductId(),product.getProductName(),product.getPrice(),product.getImage(),
				product.getColor(),product.getCategory(),product.getQuantity(),product.getManufacturer(),product.getSpecification());
		return productDetails;
	}
    

    private List<ProductDetails> toDetails(List<ProductEntity> products) {
    	Log.info("Inside toDetails products:"+products);
    	List<ProductDetails> productDetails = new ArrayList<>();
        for (ProductEntity product : products) {
        	ProductDetails details = toDetails(product);
            productDetails.add(details);
        }
    	return productDetails;
	}

}
