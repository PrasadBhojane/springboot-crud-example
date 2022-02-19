package com.springboot.crud.entity;

import java.util.HashSet;
import java.util.Set;

public class Cart {

	private Integer cartId;
	
	private String cartCode;
	
	private Set<Product> products = new HashSet<Product>();
	
	private int totalPrice;
}
