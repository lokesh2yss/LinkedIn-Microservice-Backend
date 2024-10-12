package com.codingshuttle.linkedin.post_service.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus status;

    private String error;

    private LocalDateTime timeStamp;

    public ApiError() {
        timeStamp = LocalDateTime.now();
    }
    public ApiError(String error, HttpStatus status) {
        this();
        this.error = error;
        this.status = status;
    }
}
