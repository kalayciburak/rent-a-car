package com.kalayciburak.corepackage.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseItem<T> {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String type;
    private String code;
    private Object message;
    private boolean success;
    private int count;
    private T data;

    public ResponseItem(String type, String code, Object message, boolean success) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ResponseItem(String type, String code, T data, Object message, boolean success, int count) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.code = code;
        this.message = message;
        this.success = success;
        this.count = count;
        this.data = data;
    }
}