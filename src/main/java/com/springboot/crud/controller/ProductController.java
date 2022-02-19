package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.springboot.crud.entity.AccessToken;
import com.springboot.crud.entity.Category;
import com.springboot.crud.entity.Product;
import com.springboot.crud.service.ProductService;
import com.springboot.crud.util.AmazonUtil;

@RestController
@RequestMapping("/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	AccessToken acessToken;
	
	String uuidString;
	
	@Value("${masterToken}")
	private String masterToken;
	
	@Autowired
	AmazonUtil utility; 
	

	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<AccessToken> loginCheck(@PathVariable("userName") String userName,
			@PathVariable("password") String password) throws Exception {
		String user = "paddy";
		String pass = "paddy123";
		if (userName.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)) {
			acessToken = new AccessToken();
			acessToken.setToken(masterToken);
		} else {
			throw new Exception("Credentials not valid");
		}
		return new ResponseEntity<AccessToken>(acessToken, HttpStatus.OK);
	}

	@PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product saveProduct = productService.saveProduct(product);

		return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
	}

	@PostMapping(value = "/addCategory", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestHeader("token") String token)
			throws Exception {
		Category saveCategory = null;
		ResponseEntity<Category> responseEntity;
		if (utility.isTokenValid(token)) {
			saveCategory = productService.saveCategory(category);
			responseEntity = new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
		} else {
			throw new Exception("Token is non valid");
		}
		return responseEntity;
	}

	@PostMapping(value = "/addProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
		List<Product> saveProducts = productService.saveProducts(products);
		
		return new ResponseEntity<>(saveProducts, HttpStatus.CREATED);
	}

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findAllProducts() {
		List<Product> getProducts = productService.getProducts();
		
		return new ResponseEntity<>(getProducts, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/productById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findProductById(@PathVariable int id) {
		Product getProductById = productService.getProductById(id);
		
		return new ResponseEntity<>(getProductById, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findProductByName(@PathVariable String name) {
		Product getProductByName = productService.getProductByName(name);
		
		return new ResponseEntity<>(getProductByName, HttpStatus.ACCEPTED);
	}

//	@PutMapping("/update")
//	public Product updateProduct(@RequestBody Product product) {
//		return productService.updateProduct(product);
//	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		String deleteProduct = productService.deleteProduct(id);
		
		return new ResponseEntity<>(deleteProduct, HttpStatus.OK);
	}

}
