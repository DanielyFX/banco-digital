package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "ResponseDepositoDTO",
        description = "Objeto de resposta para o depósito em uma conta"
)
public record ResponseDepositoDTO(
        @Schema(description = "Conta de deposito", type = "string", example = "123456") String conta,
        @Schema(description = "Agência de deposito", type = "string", example = "1234") String agencia,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) {
}
