package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.iservices.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private IProductService products;
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> get(@PathVariable int productId){
		Optional<Product> optionalProduct = products.getProductById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> get(){
		List<Product> listOfProducts = products.getAllProducts();
		if(listOfProducts == null || listOfProducts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no product");
		}
		return ResponseEntity.ok(listOfProducts);
	}
	
	@PostMapping
	public ResponseEntity<Product> add(@RequestBody Product product){
		return ResponseEntity.ok(products.createProduct(product));
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Product> update(@PathVariable int productId, @RequestBody Product product){
		return ResponseEntity.ok(products.updateProduct(productId, product));
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> delete(@PathVariable int productId){
		if(products.deleteProduct(productId)) {
			return ResponseEntity.ok("Product is deleted succesfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete product");
		}
	}
}
