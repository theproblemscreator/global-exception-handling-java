package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	public List<Product> getAllProduct() {
		return productRepo.findAll();
		
	}
	
	public Product getProductByID(int id) throws ProductNotFoundException {
		return productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found With Id : "+id));
		
	}
	
	public Product updateProduct(Product product) throws ProductNotFoundException {

	    Product existingProduct = productRepo.findById(product.getPid())
	            .orElseThrow(() -> new ProductNotFoundException("Product Not Found with Id :"+product.getPid()));

	    existingProduct.setPname(product.getPname());
	    existingProduct.setPprice(product.getPprice());
	    // set other fields

	    return productRepo.save(existingProduct);
	}
	
	public void deleteProduct(int id) throws ProductNotFoundException {
		 Product product = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found With Id :"+id));
		 productRepo.delete(product);
	}
}
