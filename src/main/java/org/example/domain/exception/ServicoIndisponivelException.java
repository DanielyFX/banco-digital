package org.example.domain.exception;

import java.io.Serial;

public class ServicoIndisponivelException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorMessage;

    public ServicoIndisponivelException(Integer errorCode, String errorMessage, Throwable cause){
        super(errorMessage, cause);
        this.errorCode = Integer.toString(errorCode);
        this.errorMessage = errorMessage;
    }

    public ServicoIndisponivelException(String errorCode, String errorMessage, Throwable cause){
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ServicoIndisponivelException(Integer errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = Integer.toString(errorCode);
        this.errorMessage = errorMessage;
    }

    public ServicoIndisponivelException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrCode(){ return this.errorCode;}

    public String getErrMsg(){ return this.errorMessage;}
}
