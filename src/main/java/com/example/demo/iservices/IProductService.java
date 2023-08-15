package com.example.demo.iservices;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Product;

public interface IProductService {
	Product createProduct(Product product);
    Optional<Product> getProductById(int productId);
    List<Product> getAllProducts();
    Product updateProduct(int productId, Product updatedProduct);
    boolean deleteProduct(int productId);
}
