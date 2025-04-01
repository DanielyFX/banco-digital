package org.example.adapter.input;

import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.output.dto.ResponseCriacaoDeContaDTO;
import org.example.port.input.OperacaoBancariaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacao-bancaria")
public class OperacaoBancariaController {

    private final OperacaoBancariaUseCase operacaoBancariaUseCase;

    public OperacaoBancariaController(OperacaoBancariaUseCase operacaoBancariaUseCase) {
        this.operacaoBancariaUseCase = operacaoBancariaUseCase;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/criar-conta-corrente")
    public ResponseEntity<ResponseCriacaoDeContaDTO> criarContaCorrente(
            @RequestHeader String titular,
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader(required = false) double saldo){
        ResponseCriacaoDeContaDTO response = operacaoBancariaUseCase.criarContaCorrente(
                RequestCriacaoContaDTO.builder()
                        .titular(titular)
                        .conta(conta)
                        .agencia(agencia)
                        .saldo(saldo)
                        .build()
                );
        return ResponseEntity.ok(response);
    }
}
