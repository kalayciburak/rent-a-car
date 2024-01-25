package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.ReferenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class City extends ReferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Location> locations;
}
