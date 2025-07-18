package com.techlab.backend.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders") 
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Long id, List<OrderLine> orderLines) {
        this.id = id;
        this.orderLines = orderLines;
    }

    public Long getId() {
        return id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }   

     public void addProduct(Product product, int quantity) {
        orderLines.add(new OrderLine(product, quantity));
    }

    public double calculateTotal() {
        return orderLines.stream().mapToDouble(OrderLine::calculatePrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order ID: " + id + "\n");
        for (OrderLine ol : orderLines) {
            sb.append("  ").append(ol).append("\n");
        }
        sb.append("TOTAL: $").append(calculateTotal());
        return sb.toString();
    }

}