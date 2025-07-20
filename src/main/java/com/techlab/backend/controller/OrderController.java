package com.techlab.backend.controller;

import com.techlab.backend.dto.*;
import com.techlab.backend.model.Order;
import com.techlab.backend.model.Product;
import com.techlab.backend.model.OrderLine;
import com.techlab.backend.service.api.OrderService;
import com.techlab.backend.service.api.ProductService;
import com.techlab.backend.exception.OrderNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    
    /**
     * GET /api/orders - Obtener todas las órdenes con información resumida
     */
    @GetMapping
    public ResponseEntity<List<OrderSummaryDto>> getAllOrdersWithSummary() {
        List<Order> orders = orderService.findAllOrders();
        List<OrderSummaryDto> orderSummaryDtos = DtoMapper.toOrderSummaryDtoList(orders);
        return ResponseEntity.ok(orderSummaryDtos);
    }

    /**
     * GET /api/orders/{id} - Obtener orden por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderSummaryDto> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        
        OrderSummaryDto orderDto = DtoMapper.toOrderSummaryDto(order.get());
        return ResponseEntity.ok(orderDto);
    }
    
    /**
     * POST /api/orders/create - Crear nueva orden con múltiples productos
     * Body: CreateOrderRequestDto with list of OrderItemDto
     */
    @PostMapping
    public ResponseEntity<OrderSummaryDto> createOrderWithProducts(@Valid @RequestBody CreateOrderRequestDto createOrderRequest) {
        Order newOrder = orderService.createOrderWithProducts(createOrderRequest);
        OrderSummaryDto orderDto = DtoMapper.toOrderSummaryDto(newOrder);
        return ResponseEntity.ok(orderDto);
    }
    
  
    
    
    /**
     * GET /api/orders/{id}/summary - Obtener resumen detallado de una orden
     */
    @GetMapping("/{id}/summary")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findOrderById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        
        Order order = orderOpt.get();
        OrderSummaryDto orderDto = DtoMapper.toOrderSummaryDto(order);
        
        return ResponseEntity.ok(orderDto);
    }
    
    /**
     * GET /api/orders/{id}/total - Calcular total de una orden
     */
    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getOrderTotal(@PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findOrderById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        
        double total = orderOpt.get().calculateTotal();
        return ResponseEntity.ok(total);
    }
    
    /**
     * DELETE /api/orders/{id} - Eliminar orden completa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderService.findOrderById(id).isPresent()) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
