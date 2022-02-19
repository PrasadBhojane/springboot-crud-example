package com.springboot.crud.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.entity.AcessToken;
import com.springboot.crud.entity.Category;
import com.springboot.crud.entity.Product;
import com.springboot.crud.service.ProductService;

@RestController
@RequestMapping("/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	AcessToken acessToken;
	
	String uuidString;

	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<AcessToken> loginCheck(@PathVariable("userName") String userName,
			@PathVariable("password") String password) throws Exception {
		String user = "paddy";
		String pass = "paddy123";
		UUID uuid = UUID.randomUUID();
		uuidString = uuid.toString();
		System.out.println("UUID -> " + uuidString);
		if (userName.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)) {
			acessToken = new AcessToken();
			acessToken.setToken(uuid.toString());
		} else {
			throw new Exception("Credentials not valid");
		}
		return new ResponseEntity<AcessToken>(acessToken, HttpStatus.OK);
	}

	@PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product saveProduct = productService.saveProduct(product);

		return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
	}

	@PostMapping(value = "/addCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestHeader("token") String token)
			throws Exception {
		Category saveCategory = null;
		ResponseEntity<Category> responseEntity;
		if (token.equals(uuidString)) {
			saveCategory = productService.saveCategory(category);
			responseEntity = new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
		} else {
			throw new Exception("Token is non valid");
		}
		return responseEntity;
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return productService.saveProducts(products);
	}

	@GetMapping("/products")
	public List<Product> findAllProducts() {
		return productService.getProducts();
	}

	@GetMapping("/productById/{id}")
	public Product findProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/product/{name}")
	public Product findProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}

//	@PutMapping("/update")
//	public Product updateProduct(@RequestBody Product product) {
//		return productService.updateProduct(product);
//	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}

}
