package com.techlab.backend.dto;

import com.techlab.backend.model.Order;
import com.techlab.backend.model.OrderLine;
import com.techlab.backend.model.Product;
import com.techlab.backend.model.Beverage;
import com.techlab.backend.model.Food;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting between entities and DTOs
 */
public class DtoMapper {
    
    /**
     * Convert Product entity to ProductDto
     */
    public static ProductDto toProductDto(Product product) {
        if (product == null) return null;
        
        ProductDto dto = new ProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock(),
            product.getClass().getSimpleName(), // "Food" or "Beverage"
            product.getDescripcion(),
            product.getImagen()
        );
        
        // Agregar campos específicos según el tipo
        if (product instanceof Beverage) {
            Beverage beverage = (Beverage) product;
            dto.setVolume(beverage.getVolumeInLiters());
        } else if (product instanceof Food) {
            Food food = (Food) product;
            dto.setExpireDate(food.getExpirationDate());
        }
        
        return dto;
    }
    
    /**
     * Convert OrderLine entity to OrderLineDto
     */
    public static OrderLineDto toOrderLineDto(OrderLine orderLine) {
        if (orderLine == null) return null;
        
        return new OrderLineDto(
            orderLine.getId(),
            toProductDto(orderLine.getProduct()),
            orderLine.getQuantity(),
            orderLine.calculatePrice() // OrderLine has calculatePrice() method
        );
    }
    
    /**
     * Convert Order entity to OrderSummaryDto
     */
    public static OrderSummaryDto toOrderSummaryDto(Order order) {
        if (order == null) return null;
        
        List<OrderLineDto> orderLineDtos = order.getOrderLines().stream()
            .map(DtoMapper::toOrderLineDto)
            .collect(Collectors.toList());
        
        return new OrderSummaryDto(
            order.getId(),
            orderLineDtos,
            order.calculateTotal(),
            order.getOrderLines().size()
        );
    }
    
    /**
     * Convert list of Products to list of ProductDtos
     */
    public static List<ProductDto> toProductDtoList(List<Product> products) {
        return products.stream()
            .map(DtoMapper::toProductDto)
            .collect(Collectors.toList());
    }
    
    /**
     * Convert list of Orders to list of OrderSummaryDtos
     */
    public static List<OrderSummaryDto> toOrderSummaryDtoList(List<Order> orders) {
        return orders.stream()
            .map(DtoMapper::toOrderSummaryDto)
            .collect(Collectors.toList());
    }
}
