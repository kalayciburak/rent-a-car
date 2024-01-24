package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "locations")
@SQLRestriction("is_active=true")
public class Location extends BaseEntity {
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "location")
    private List<Car> cars;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
