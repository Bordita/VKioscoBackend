package com.techlab.backend.model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "foods") 
public class Food extends Product {

    private String expirationDate;

    public Food(String name, double price, int stock, String expirationDate) {
        super(name, price, stock);
        this.expirationDate = expirationDate;
    }

    public Food(String name, double price, int stock, String descripcion, String imagen, String expirationDate) {
        super(name, price, stock, descripcion, imagen);
        this.expirationDate = expirationDate;
    }

    public Food() {
        super();
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Expires: %s", expirationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return this.expirationDate.equals(food.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate);
    }
    
}
