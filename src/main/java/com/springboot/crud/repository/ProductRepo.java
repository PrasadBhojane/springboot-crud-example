package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	Product findByProductName(String productName);

	public Product findByProductPriceAndProductName(double productPrice, String productName);

}
