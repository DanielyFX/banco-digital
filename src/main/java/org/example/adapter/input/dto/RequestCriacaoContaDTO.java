package org.example.adapter.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Schema(
        name = "RequestCriacaoContaDTO",
        description = "DTO para criação de conta",
        example = """
                {
                     "conta": 1568,
                     "agencia": 258,
                     "titular": "João da Silva",
                     "saldo": 1000.0
                }
                """
)
public record RequestCriacaoContaDTO(
        @Schema(description = "Nome do titular da conta", type = "integer", example = "João da Silva", required = true) String titular,
        @Schema(description = "Número da conta", type = "integer", example = "1568", required = true) int conta,
        @Schema(description = "Número da agência", type = "integer", example = "258", required = true) int agencia,
        @Schema(description = "Saldo inicial da conta", type = "integer", example = "1000.0", required = true) double saldo
) implements Serializable {
}
