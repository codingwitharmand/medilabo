package com.cwa.medilaboback;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private int code;
    private String message;
    private LocalDateTime timestamp;
}
