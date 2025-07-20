package com.techlab.backend.repository;

import com.techlab.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    /**
     * Encuentra órdenes que contengan un producto específico
     */
    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderLines ol WHERE ol.product.id = :productId")
    List<Order> findOrdersByProductId(@Param("productId") Long productId);
    
    /**
     * Encuentra órdenes con total mayor al especificado
     */
    @Query("SELECT o FROM Order o WHERE (SELECT SUM(ol.quantity * ol.product.price) FROM Order o2 JOIN o2.orderLines ol WHERE o2.id = o.id) > :minTotal")
    List<Order> findOrdersWithTotalGreaterThan(@Param("minTotal") double minTotal);
}
