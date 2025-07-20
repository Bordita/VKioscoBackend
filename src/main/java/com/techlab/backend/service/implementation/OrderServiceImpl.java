package com.techlab.backend.service.implementation;

import com.techlab.backend.model.Order;
import com.techlab.backend.model.Product;
import com.techlab.backend.repository.OrderRepository;
import com.techlab.backend.service.api.OrderService;
import com.techlab.backend.service.api.ProductService;
import com.techlab.backend.dto.CreateOrderRequestDto;
import com.techlab.backend.dto.OrderItemDto;
import com.techlab.backend.exception.ProductNotFoundException;
import com.techlab.backend.exception.InsufficientStockException;
import com.techlab.backend.exception.OrderNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductService productService;
    
    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    
    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Orden con ID " + id + " no encontrada");
        }
        orderRepository.deleteById(id);
    }
    
    
    @Override
    @Transactional
    public Order createOrderWithProducts(CreateOrderRequestDto createOrderRequest) {
        Order newOrder = new Order(null, new ArrayList<>());
        

        for (OrderItemDto item : createOrderRequest.getOrderItems()) {
            Product product = productService.findProductById(item.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + item.getProductId() + " no encontrado"));
            
            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException(
                    "Stock insuficiente para el producto " + product.getName() + 
                    ". Disponible: " + product.getStock() + ", Solicitado: " + item.getQuantity()
                );
            }
            
            newOrder.addProduct(product, item.getQuantity());
            
            product.setStock(product.getStock() - item.getQuantity());
            productService.saveProduct(product);
        }
        return orderRepository.save(newOrder);
    }
    
    
    @Override
    public List<Order> findOrdersByProductId(Long productId) {
        return orderRepository.findOrdersByProductId(productId);
    }
    
    @Override
    public List<Order> findOrdersWithTotalGreaterThan(double minTotal) {
        return orderRepository.findOrdersWithTotalGreaterThan(minTotal);
    }
}
