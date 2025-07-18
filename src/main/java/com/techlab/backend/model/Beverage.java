package com.techlab.backend.model;

import java.util.Objects;
import jakarta.persistence.*;


@Entity
@Table(name = "beverages") 
public class Beverage extends Product {

    private double volumeInLiters;

    public Beverage(String name, double price, int stock, double volumeInLiters) {
        super(name, price, stock);
        this.volumeInLiters = volumeInLiters;
    }

    public Beverage() {
        super();
    }

    public double getVolumeInLiters() {
        return volumeInLiters;
    }

    public void setVolumeInLiters(double volumeInLiters) {
        this.volumeInLiters = volumeInLiters;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Volume: %.2f L", volumeInLiters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beverage)) return false;
        if (!super.equals(o)) return false;
        Beverage Beverage = (Beverage) o;
        return Double.compare(Beverage.volumeInLiters, volumeInLiters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volumeInLiters);
    }
    
}
