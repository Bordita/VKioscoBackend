package com.techlab.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
