package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.services.ProductService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Transactional
	@PostMapping("/save")
	public void saveProduct(@RequestBody Product product) {

		productService.saveProduct(product);
	}
	
	@GetMapping()
	public List<Product> getProduct() {
		return productService.getAllProduct();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) throws ProductNotFoundException {
		return productService.getProductByID(id);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) throws ProductNotFoundException {

	    product.setPid(id);  
	    return productService.updateProduct(product);
	}

	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		 productService.deleteProduct(id);
}
}
