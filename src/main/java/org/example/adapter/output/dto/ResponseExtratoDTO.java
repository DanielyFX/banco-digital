package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ResponseExtratoDTO(
        @Schema(description = "Transação realizada", type = "string", example = "Saque") String transacao,
        @Schema(description = "Saldo atual", type = "string", example = "1000.0") String saldo,
        @Schema(description = "Descrição da transação", type = "string", example = "Saque em caixa eletrônico") String descricao,
        @Schema(description = "Data da transação", type = "string", example = "2023-10-01T10:00:00") String data,
        @Schema(description = "Valor da transação", type = "string", example = "1000.0") String valor
) implements Serializable {
}
