package org.example.adapter.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "ResponseCriacaoDeConta",
        description = "Objeto de resposta para a criação de uma conta",
        example = """
                
                {
                    "conta": 123456,
                    "agencia": 1234,
                    "detail": {
                        "message": "Conta criada com sucesso",
                        "code": 201,
                        "timestampInicio": "2021-08-01T00:00:00",
                        "timestampFim": "2021-08-01T00:00:01"
                    }
                }
                """
)
public record ResponseCriacaoDeContaDTO(
        @Schema(description = "Número da conta", type = "integer", example = "123456") int conta,
        @Schema(description = "Número da agência", type = "integer", example = "1234") int agencia,
        @Schema(description = "Detalhe da chamada", type = "object", implementation = DetailDTO.class) DetailDTO detail
) {
}
