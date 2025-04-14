package org.example.adapter.exception.handler;

import org.example.adapter.exception.handler.response.ApiErroResponse;
import org.example.adapter.exception.infrastructure.InfrastructureException;
import org.example.domain.exception.ContaNaoEncontradaException;
import org.example.domain.exception.ServicoIndisponivelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler {

    private static final Logger LOGGER_TECNICO = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<Object> handleException(ContaNaoEncontradaException ex){
        var apiErro = new ApiErroResponse(HttpStatus.NO_CONTENT, ex);
        apiErro.addErroNegocio(ex.getErrCode(), ex.getErrMsg());
        return buildResponseEntity(apiErro, ex);
    }

    @ExceptionHandler(ServicoIndisponivelException.class)
    public ResponseEntity<Object> handleException(ServicoIndisponivelException ex){
        var apiErro = new ApiErroResponse(HttpStatus.SERVICE_UNAVAILABLE, ex);
        apiErro.setMensagem(ex.getMessage());
        if (ex.getCause() != null) {
            apiErro.setMensagem(ex.getCause().getMessage());
        }
        return buildResponseEntity(apiErro, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErroResponse apiErro, Exception ex){
        LOGGER_TECNICO.error("Exceção sendo capturada, APIErrorCode: {}, Mensagem: {}, Excecao: ", apiErro.getCodigoErro(), apiErro.getMensagem(), ex);
        return new ResponseEntity<>(apiErro, apiErro.getStatus());
    }


}
