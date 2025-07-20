package com.techlab.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;


public class CreateOrderRequestDto {
    
    @NotEmpty(message = "Los artículos del pedido no pueden estar vacíos")
    @Valid
    private List<OrderItemDto> orderItems;
    
    public CreateOrderRequestDto() {}
    
    public CreateOrderRequestDto(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
    
    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
    
    @Override
    public String toString() {
        return "CreateOrderRequestDto{" +
                "articulosDelPedido=" + orderItems +
                '}';
    }
}
