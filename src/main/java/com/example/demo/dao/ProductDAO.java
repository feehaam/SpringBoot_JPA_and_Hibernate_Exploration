package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
}
