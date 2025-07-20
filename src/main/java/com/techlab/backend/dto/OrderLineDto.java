package com.techlab.backend.dto;

public class OrderLineDto {
    
    private Long id;
    private ProductDto product;
    private Integer quantity;
    private Double subtotal;
    
    public OrderLineDto() {}
    
    public OrderLineDto(Long id, ProductDto product, Integer quantity, Double subtotal) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductDto getProduct() {
        return product;
    }
    
    public void setProduct(ProductDto product) {
        this.product = product;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    @Override
    public String toString() {
        return "OrderLineDto{" +
                "id=" + id +
                ", producto=" + product +
                ", cantidad=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
