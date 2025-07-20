package com.techlab.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class OrderItemDto {
    
    @NotNull(message = "El ID del producto es requerido")
    private Long productId;
    
    @NotNull(message = "La cantidad es requerida")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;
    
    public OrderItemDto() {}
    
    public OrderItemDto(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "OrderItemDto{" +
                "idProducto=" + productId +
                ", cantidad=" + quantity +
                '}';
    }
}
