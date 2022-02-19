package com.springboot.crud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private Integer productId;
	private String productName;
	private int productQuantity;
	private int productPrice;
	private String productCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId",referencedColumnName = "categoryId")
	private Category category;
	
	
}
