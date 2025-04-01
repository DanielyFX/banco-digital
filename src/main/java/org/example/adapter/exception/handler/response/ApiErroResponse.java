package org.example.adapter.exception.handler.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Schema(
        name = "Api_Error_Response",
        description = "Modelo para retorno de erros da API",
        example = """
                {
                    apierro: {
                         "timestamp": "2021-08-01T00:00:00",
                         "status": "INTERNAL_SERVER_ERROR",
                         "code": 500,
                         "message": "Erro ao processar a requisição",
                         "mensagemDetalhada": "Erro ao processar a requisição",
                         "subErros": []
                    }
                }
                """
)
@JsonTypeName("apierro")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ApiErroResponse {
    @Schema(description = "Timestamp do erro", example = "2021-08-01T00:00:00")
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp;

    @Schema(description = "Status do erro", example = "INTERNAL_SERVER_ERROR")
    @JsonProperty("status")
    private HttpStatus status;

    @Schema(description = "Código do erro", example = "500")
    @JsonProperty("codigoErro")
    private Integer codigoErro;

    @Schema(description = "Mensagem do erro", example = "Erro ao processar a requisição")
    @JsonProperty("message")
    private String mensagem;

    @Schema(description = "Mensagem detalhada do erro", example = "Erro ao processar a requisição")
    @JsonProperty("mensagemDetalhada")
    private String mensagemDetalhada;

    @Schema(description = "Sub erros do erro", example = "[]")
    @JsonProperty("subErros")
    private List<ApiSubErroResponse> subErros = new ArrayList<>();

    private ApiErroResponse(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiErroResponse(HttpStatus status){
        this();
        this.status = status;
        this.codigoErro = status.value();
    }

    public ApiErroResponse(HttpStatus status, Throwable ex){
        this();
        this.status = status;
        this.codigoErro = status.value();
        this.mensagem = "Erro inesperado";
        this.mensagemDetalhada = ex.getLocalizedMessage();
    }

    private void addSubErro(ApiSubErroResponse subErro){
        if(subErros == null){
            subErros = new ArrayList<>();
        }
        subErros.add(subErro);
    }

    private void addValidacaoErro(String object, String field, Object rejectedValue, String message){
        addSubErro(new ApiValidacaoErroResponse(object, field, rejectedValue, message));
    }

    private void addValidacaoErro(String object, String message){
        addSubErro(new ApiValidacaoErroResponse(object, message));
    }

    public void addErroNegocio(String codigo, String message){
        addSubErro(new SubErroNegocio(codigo, message));
    }

    private void addValidacaoErro(FieldError fieldError){
        this.addValidacaoErro(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
        );
    }

    public void addValidacaoErrors(List<FieldError> fieldErrors){
        fieldErrors.forEach(this::addValidacaoErro);
    }

    public void addValidacaoErro(ObjectError objectError){
        this.addValidacaoErro(
                objectError.getObjectName(),
                objectError.getDefaultMessage()
        );
    }

    public void addValidacaoErro(ConstraintViolation<?> cv){
        this.addValidacaoErro(
                cv.getRootBeanClass().getSimpleName(),
                cv.getPropertyPath().toString(),
                cv.getInvalidValue(),
                cv.getMessage()
        );
    }

    public void addValidacaoErro(Set<ConstraintViolation<?>> constraintViolations){
        constraintViolations.forEach(this::addValidacaoErro);
    }

    public HttpStatus getStatus(){
        return status;
    }

    public Integer getCodigoErro(){
        return codigoErro;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public String getMensagem(){
        return mensagem;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public void getMensagemDetalhada(String mensagemDetalhada){
        this.mensagemDetalhada = mensagemDetalhada;
    }

    public void setMensagemDetalhada(String mensagemDetalhada){
        this.mensagemDetalhada = mensagemDetalhada;
    }

    public List<ApiSubErroResponse> getSubErros(){
        return subErros;
    }
}
