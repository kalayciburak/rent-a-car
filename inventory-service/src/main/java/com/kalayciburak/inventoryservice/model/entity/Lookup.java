package com.kalayciburak.inventoryservice.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kalayciburak.corepackage.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Lookup getParent() {
        return parent;
    }

    public void setParent(Lookup parent) {
        this.parent = parent;
    }

    public List<Lookup> getChildren() {
        return children;
    }

    public void setChildren(List<Lookup> children) {
        this.children = children;
    }
}