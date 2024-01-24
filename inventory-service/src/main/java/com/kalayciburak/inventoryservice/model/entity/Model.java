package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "models")
@SQLRestriction("is_active=true")
public class Model extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
