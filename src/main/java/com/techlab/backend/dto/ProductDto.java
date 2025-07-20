package com.techlab.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para representar un producto
 */
public class ProductDto {
    
    private Long id;
    
    @NotBlank(message = "El nombre del producto es requerido")
    private String name;
    
    @NotNull(message = "El precio es requerido")
    @Min(value = 0, message = "El precio debe ser no negativo")
    private Double price;
    
    @NotNull(message = "El stock es requerido")
    @Min(value = 0, message = "El stock debe ser no negativo")
    private Integer stock;
    
    private String productType; 
    
    private String descripcion;
    
    private String imagen;

    private Double volume; 

    private String expireDate;
    
    // Constructors
    public ProductDto() {}
    
    public ProductDto(Long id, String name, Double price, Integer stock, String productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
    }

    public ProductDto(Long id, String name, Double price, Integer stock, String productType, String descripcion, String imagen) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    
    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", precio=" + price +
                ", stock=" + stock +
                ", tipoProducto='" + productType + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", volumen=" + volume +
                ", fechaVencimiento='" + expireDate + '\'' +
                '}';
    }
}
