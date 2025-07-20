package com.techlab.backend.dto;

public class OrderOperationResponseDto {
    
    private boolean success;
    private String message;
    private OrderSummaryDto order;
    private Double newTotal;
    private Integer stockReturned;
    
    public OrderOperationResponseDto() {}
    
    public OrderOperationResponseDto(boolean success, String message, OrderSummaryDto order, Double newTotal) {
        this.success = success;
        this.message = message;
        this.order = order;
        this.newTotal = newTotal;
    }
    
    public OrderOperationResponseDto(boolean success, String message, OrderSummaryDto order, Double newTotal, Integer stockReturned) {
        this.success = success;
        this.message = message;
        this.order = order;
        this.newTotal = newTotal;
        this.stockReturned = stockReturned;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public OrderSummaryDto getOrder() {
        return order;
    }
    
    public void setOrder(OrderSummaryDto order) {
        this.order = order;
    }
    
    public Double getNewTotal() {
        return newTotal;
    }
    
    public void setNewTotal(Double newTotal) {
        this.newTotal = newTotal;
    }
    
    public Integer getStockReturned() {
        return stockReturned;
    }
    
    public void setStockReturned(Integer stockReturned) {
        this.stockReturned = stockReturned;
    }
    
    @Override
    public String toString() {
        return "OrderOperationResponseDto{" +
                "exito=" + success +
                ", mensaje='" + message + '\'' +
                ", pedido=" + order +
                ", nuevoTotal=" + newTotal +
                ", stockDevuelto=" + stockReturned +
                '}';
    }
}
