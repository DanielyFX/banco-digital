package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ResponseTransferenciaDTO(
        @Schema(description = "Conta de origem", type = "string", example = "123456") String contaOrigem,
        @Schema(description = "Agência de origem", type = "string", example = "1234") String agenciaOrigem,
        @Schema(description = "Conta de destino", type = "string", example = "654321") String contaDestino,
        @Schema(description = "Agência de destino", type = "string", example = "4321") String agenciaDestino,
        @Schema(description = "Valor da transferência", type = "string", example = "1000.0") String valor,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) implements Serializable {
}
