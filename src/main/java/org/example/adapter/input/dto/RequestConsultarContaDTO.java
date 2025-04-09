package org.example.adapter.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Schema(
        name = "RequestConsultarConta",
        description = "DTO para consulta de conta",
        example = """
                {
                    "conta": 1568,
                    "agencia": 258
                }
                """
)
public record RequestConsultarContaDTO(
        @Schema(description = "Número da conta", type = "integer", example = "1568", required = true) String conta,
        @Schema(description = "Número da agência", type = "integer", example = "258", required = true) String agencia
) implements Serializable {
}
