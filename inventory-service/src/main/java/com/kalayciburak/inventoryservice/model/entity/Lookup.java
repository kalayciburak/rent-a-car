package com.kalayciburak.inventoryservice.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kalayciburak.commonpackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lookups")
@SQLRestriction("is_active=true")
public class Lookup extends BaseEntity {
    @Column(name = "`key`")
    private String key;

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Lookup parent;

    @JsonManagedReference
    @OneToMany(mappedBy = "parent")
    private List<Lookup> children = new ArrayList<>();
}