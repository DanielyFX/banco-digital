package org.example.adapter.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record RequestTransferenciaDTO(
        @Schema(description = "Número da conta de origem", type = "string", example = "12568") String contaOrigem,
        @Schema(description = "Número da conta de destino", type = "string", example = "258") String contaDestino,
        @Schema(description = "Agência da conta de origem", type = "string", example = "258") String agenciaOrigem,
        @Schema(description = "Agência da conta de destino", type = "string", example = "258") String agenciaDestino,
        @Schema(description = "Valor da transferência", type = "string", example = "1000.0") String valor
) implements Serializable {
}
