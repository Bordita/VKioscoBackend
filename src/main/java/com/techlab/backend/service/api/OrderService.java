package com.techlab.backend.service.api;

import com.techlab.backend.dto.CreateOrderRequestDto;
import com.techlab.backend.model.Order;


import java.util.List;
import java.util.Optional;

public interface OrderService {
    
    /**
     * Encuentra todas las órdenes
     * @return Lista de todas las órdenes
     */
    List<Order> findAllOrders();
    
    /**
     * Encuentra una orden por su ID
     * @param id ID de la orden
     * @return Optional con la orden si existe
     */
    Optional<Order> findOrderById(Long id);
    
    /**
     * Guarda una orden
     * @param order Orden a guardar
     * @return Orden guardada
     */
    Order saveOrder(Order order);
    
    /**
     * Elimina una orden por su ID
     * @param id ID de la orden a eliminar
     */
    void deleteOrder(Long id);
    
    
    /**
     * Crea una nueva orden con múltiples productos
     * @param createOrderRequest DTO con los productos a agregar
     * @return Nueva orden creada con productos
     */
    Order createOrderWithProducts(CreateOrderRequestDto createOrderRequest);
    

    /**
     * Encuentra órdenes que contengan un producto específico
     * @param productId ID del producto
     * @return Lista de órdenes que contienen el producto
     */
    List<Order> findOrdersByProductId(Long productId);
    
    /**
     * Encuentra órdenes con un total mayor al especificado
     * @param minTotal Total mínimo
     * @return Lista de órdenes que superan el total mínimo
     */
    List<Order> findOrdersWithTotalGreaterThan(double minTotal);
}
