package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Variation;

public interface VariationDAO extends JpaRepository<Variation, Integer>{

}
