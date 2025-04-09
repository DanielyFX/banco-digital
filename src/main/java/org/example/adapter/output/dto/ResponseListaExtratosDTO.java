package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record ResponseListaExtratosDTO(
        @Schema(description = "Número da conta", type = "string", example = "12568") String conta,
        @Schema(description = "Agência da conta", type = "string", example = "258") String agencia,
        @ArraySchema(schema = @Schema(implementation = ResponseExtratoDTO.class, description = "Lista de extratos")) List<ResponseExtratoDTO> extratos,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) implements Serializable {
}
