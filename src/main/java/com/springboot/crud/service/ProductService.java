package com.springboot.crud.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.entity.Category;
import com.springboot.crud.entity.Product;
import com.springboot.crud.repository.CategoryRepo;
import com.springboot.crud.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Category saveCategory(Category category) {
		productRepo.saveAll(category.getProducts());
		Set<Product> products = category.getProducts();
		category.setProducts(products);
		return categoryRepo.save(category);
	}

	public List<Product> saveProducts(List<Product> products) {
		return productRepo.saveAll(products);
	}

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(int id) {
		return productRepo.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return productRepo.findByProductName(name);
	}

	public String deleteProduct(int id) {
		productRepo.deleteById(id);
		return "product removed !!" + id;
	}

//	public Product updateProduct(Product product) {
//		Product existingProduct = repository.findById(product.getId()).orElse(null);
//		existingProduct.setProductName(product.getProductName());
//		existingProduct.setProductQuantity(product.getProductQuantity());
//		existingProduct.setProductPrice(product.getProductPrice());
//		return repository.save(existingProduct);
//	}

}
