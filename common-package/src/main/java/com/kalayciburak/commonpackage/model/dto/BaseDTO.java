package com.kalayciburak.commonpackage.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDTO extends ReferenceDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private boolean isActive;
    private Long createdBy;
    private Long updatedBy;
    private Long deletedBy;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}