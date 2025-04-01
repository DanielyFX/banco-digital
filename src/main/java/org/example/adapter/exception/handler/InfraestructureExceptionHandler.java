package org.example.adapter.exception.handler;

import org.example.adapter.exception.handler.response.ApiErroResponse;
import org.example.adapter.exception.infrastructure.InfrastructureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class InfraestructureExceptionHandler {
    private static final Logger LOGGER_TECNICO = LoggerFactory.getLogger(InfraestructureExceptionHandler .class);

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<Object> hadleException(InfrastructureException ex){
        var apiErro = new ApiErroResponse(HttpStatus.SERVICE_UNAVAILABLE, ex);
        apiErro.setMensagem(ex.getMessage());
        if (ex.getCause() != null){
            apiErro.setMensagemDetalhada(ex.getCause().getMessage());
        }
        return buildResponseEntity(apiErro, ex);

    }

    private ResponseEntity<Object> buildResponseEntity(ApiErroResponse apiErro, InfrastructureException ex){
        LOGGER_TECNICO.error("Erro de infraestrutura: {}", ex.getMessage());
        return new ResponseEntity<>(apiErro, apiErro.getStatus());
    }
}
