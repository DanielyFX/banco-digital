package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record ResponseListaContasBancariasDTO(
        @ArraySchema(schema = @Schema(implementation = ResponseExtratoDTO.class, description = "Lista de extratos")) List<ResponseExtratoDTO> extratos,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) implements Serializable {
}
