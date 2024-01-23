package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.corepackage.model.entity.BaseEntity;
import com.kalayciburak.inventoryservice.model.enums.CarStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

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

    @Column(name = "car_status")
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Lookup getFuelType() {
        return fuelType;
    }

    public void setFuelType(Lookup fuelType) {
        this.fuelType = fuelType;
    }

    public Lookup getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(Lookup transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Lookup getColorType() {
        return colorType;
    }

    public void setColorType(Lookup colorType) {
        this.colorType = colorType;
    }
}
