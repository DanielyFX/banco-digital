package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Schema(
        name = "DetailDTO",
        description = "Objeto para detalhar informações do retorno da requisição",
        example =
                """
                {
                    "codigoRetorno": "200",
                    "mensagemRetorno": "Operação realizada com sucesso",
                    "timestampInicio": "2021-08-01T00:00:00",
                    "timestampFim": "2021-08-01T00:00:01"
                }
                """
)
public record DetailDTO(
        @Schema(description = "Código de retorno da requisição", type = "integer", example = "200") String codigoRetorno,
        @Schema(description = "Mensagem de retorno da requisição", type = "integer", example = "Operação realizada com sucesso") String mensagemRetorno,
        @Schema(description = "Timestamp de início da requisição", type = "integer", example = "2021-08-01T00:00:00") String timestampInicio,
        @Schema(description = "Timestamp de fim da requisição", type = "integer", example = "2021-08-01T00:00:01") String timestampFim
) implements Serializable {
}
