package com.kalayciburak.commonpackage.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends ReferenceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private LocalDateTime createdAt;

    @Column(name = "updated", insertable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private boolean isActive = true;

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}