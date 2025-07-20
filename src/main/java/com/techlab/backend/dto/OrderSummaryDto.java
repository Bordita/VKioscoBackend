package com.techlab.backend.dto;

import java.util.List;

public class OrderSummaryDto {
    
    private Long id;
    private List<OrderLineDto> lineasDeOrden;
    private Double montoTotal;
    private Integer numeroDeArticulos;
    
    public OrderSummaryDto() {}
    
    public OrderSummaryDto(Long id, List<OrderLineDto> lineasDeOrden, Double montoTotal, Integer numeroDeArticulos) {
        this.id = id;
        this.lineasDeOrden = lineasDeOrden;
        this.montoTotal = montoTotal;
        this.numeroDeArticulos = numeroDeArticulos;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<OrderLineDto> getLineasDeOrden() {
        return lineasDeOrden;
    }
    
    public void setLineasDeOrden(List<OrderLineDto> lineasDeOrden) {
        this.lineasDeOrden = lineasDeOrden;
    }
    
    public Double getMontoTotal() {
        return montoTotal;
    }
    
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public Integer getNumeroDeArticulos() {
        return numeroDeArticulos;
    }
    
    public void setNumeroDeArticulos(Integer numeroDeArticulos) {
        this.numeroDeArticulos = numeroDeArticulos;
    }
    
    @Override
    public String toString() {
        return "OrderSummaryDto{" +
                "id=" + id +
                ", lineasDeOrden=" + lineasDeOrden +
                ", montoTotal=" + montoTotal +
                ", numeroDeArticulos=" + numeroDeArticulos +
                '}';
    }
}
