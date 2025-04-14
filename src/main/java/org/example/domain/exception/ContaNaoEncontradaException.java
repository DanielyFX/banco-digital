package org.example.domain.exception;

import org.example.domain.entities.Conta;

import java.io.Serial;

public class ContaNaoEncontradaException extends BusinessException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException(String errorCode, String errorMsg, Throwable cause){
        super(errorCode, errorMsg, cause);
    }

    public ContaNaoEncontradaException(String errorCode, String errorMsg){
        super(errorCode, errorMsg);
    }
}
