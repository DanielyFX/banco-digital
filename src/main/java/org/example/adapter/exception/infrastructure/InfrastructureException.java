package org.example.adapter.exception.infrastructure;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;

import java.io.Serial;

public class InfrastructureException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String codigoError;

    public InfrastructureException(String codigoError, String messageError){
        super(messageError);
        this.codigoError = codigoError;
    }
    public InfrastructureException(String codigoError, String messageError, Throwable cause) {
        super(messageError, cause);
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }
}
