package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
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
}
