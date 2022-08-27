package com.krugger.vacunacion.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRequest {
    private String errorMessage;
    private int errorCode;
    private Throwable errorCause;

    public ErrorRequest(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
