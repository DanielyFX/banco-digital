package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Schema(
        name = "ResponseSaque",
        description = "Objeto de resposta para o saque",
        example = """
                
                {
                    "conta": 123456,
                    "agencia": 1234,
                    "valorSacado": 100.0,
                    "detail": {
                        "message": "Saque realizado com sucesso",
                        "code": 200,
                        "timestampInicio": "2021-08-01T00:00:00",
                        "timestampFim": "2021-08-01T00:00:01"
                    }
                }
                """
)
public record ResponseSaqueDTO(
        @Schema(description = "Número da conta", type = "integer", example = "123456") String conta,
        @Schema(description = "Número da agência", type = "integer", example = "1234") String agencia,
        @Schema(description = "Saldo", type = "integer", example = "100.0") double saldo,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) implements Serializable {
}
