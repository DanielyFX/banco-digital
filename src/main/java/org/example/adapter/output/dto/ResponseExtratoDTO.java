package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ResponseExtratoDTO(
        @Schema(description = "Conta de deposito", type = "string", example = "123456") String conta,
        @Schema(description = "Agência de deposito", type = "string", example = "1234") String agencia,
        @Schema(description = "Transação realizada", type = "string", example = "Saque") String transacao,
        @Schema(description = "Descrição da transação", type = "string", example = "Saque em caixa eletrônico") String descricao,
        @Schema(description = "Data da transação", type = "string", example = "2023-10-01T10:00:00") String hora,
        @Schema(description = "Valor da transação", type = "string", example = "1000.0") String valor
) implements Serializable {
}
