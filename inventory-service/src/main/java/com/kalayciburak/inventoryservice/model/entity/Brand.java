package com.kalayciburak.inventoryservice.model.entity;

import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brands")
@SQLRestriction("is_active=true")
public class Brand extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;
}
