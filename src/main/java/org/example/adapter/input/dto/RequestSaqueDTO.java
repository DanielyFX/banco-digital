package org.example.adapter.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Schema(
        name = "RequestSacarDTO",
        description = "DTO para saque de conta",
        example = """
                {
                     "conta": 1568,
                     "agencia": 258,
                     "valor": 100.0
                }
                """
)
public record RequestSaqueDTO(
        @Schema(description = "Número da conta", type = "integer", example = "1568", required = true) String conta,
        @Schema(description = "Número da agência", type = "integer", example = "258", required = true) String agencia,
        @Schema(description = "Valor a ser sacado", type = "integer", example = "100.0", required = true) String valor
) implements Serializable {
}
