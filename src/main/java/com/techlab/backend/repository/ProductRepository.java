package com.techlab.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techlab.backend.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Encuentra un producto por nombre exacto (ignorando mayúsculas)
     */
    Product findByNameIgnoreCase(String name);
    
    /**
     * Encuentra productos que contengan el texto en el nombre
     */
    List<Product> findByNameContainingIgnoreCase(String name);
    
    /**
     * Encuentra productos con stock menor al especificado
     */
    List<Product> findByStockLessThan(int stock);
    
    /**
     * Consulta personalizada para encontrar productos por tipo específico
     */
    @Query("SELECT p FROM Product p WHERE TYPE(p) = :productType")
    List<Product> findByProductType(@Param("productType") Class<? extends Product> productType);
}
