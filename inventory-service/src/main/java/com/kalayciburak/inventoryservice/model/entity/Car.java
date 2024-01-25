package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "cars")
@SQLRestriction("is_active=true")
public class Car extends BaseEntity {
    @Column(name = "year")
    private int year;

    @Column(name = "plate")
    private String plate;

    @Column(name = "daily_price")
    private double dailyPrice;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private Lookup fuelType;

    @ManyToOne
    @JoinColumn(name = "transmission_type_id")
    private Lookup transmissionType;

    @ManyToOne
    @JoinColumn(name = "color_type_id")
    private Lookup colorType;

    @ManyToOne
    @JoinColumn(name = "car_status_id")
    private Lookup carStatus;
}
