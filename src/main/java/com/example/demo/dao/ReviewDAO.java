package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Review;

public interface ReviewDAO extends JpaRepository<Review, Integer> {

}
