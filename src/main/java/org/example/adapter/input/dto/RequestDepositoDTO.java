package org.example.adapter.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record RequestDepositoDTO(
        @Schema(description = "Número da conta", type = "string", example = "12568") String conta,
        @Schema(description = "Agência da conta", type = "string", example = "258") String agencia,
        @Schema(description = "Valor do depósito", type = "string", example = "1000.0") String valor
) implements Serializable {
}
