package com.example.demo.services;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entities.Product;
import com.example.demo.iservices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductDAO productDao;
	
	@Override
	public Product createProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public Optional<Product> getProductById(int productId) {
		return productDao.findById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		/*
		if(productDao.findAll().size() == 0) {
			List<Product> list = new ArrayList<>();
			list.add(Product.getDemo());
			list.add(Product.getDemo());
			list.add(Product.getDemo());
			return list;
		}
		*/
		return productDao.findAll();
	}

	@Override
	public Product updateProduct(int productId, Product updatedProduct) {
		Optional<Product> existingProduct = getProductById(productId);
		if(existingProduct.isPresent()) {
			updatedProduct.setId(productId);
			productDao.save(updatedProduct);
			return updatedProduct; 
		}
		return null;
	}

	@Override
	public boolean deleteProduct(int productId) {
		if(productDao.existsById(productId)) {
			productDao.deleteById(productId);
			return true;
		}
		return false;
	}

    
}
