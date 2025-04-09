package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Schema(
        name = "ResponseConsultaConta",
        description = "DTO para consulta de conta",
        example = """
                {
                     "titular": "João da Silva",
                     "conta": 123456,
                     "agencia": 1234,
                     "saldo": 1000.0,
                     "detail": {
                            "codigoRetorno": "200",
                            "mensagemRetorno": "Operação realizada com sucesso",
                            "timestampInicio": "2021-08-01T00:00:00",
                            "timestampFim": "2021-08-01T00:00:01"
                     }
                }
                """
)
public record ResponseConsultaContaDTO(
        @Schema(description = "Nome do titular da conta", type = "string", example = "João da Silva") String titular,
        @Schema(description = "Número da conta", type = "integer", example = "123456") int conta,
        @Schema(description = "Número da agência", type = "integer", example = "1234") int agencia,
        @Schema(description = "Saldo da conta", type = "number", example = "1000.0") double saldo,
        @Schema(description = "Detalhe sobre a requisição", type = "object", implementation = DetailDTO.class) DetailDTO detail
) implements Serializable {
}
