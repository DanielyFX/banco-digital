package org.example.adapter.input;

import org.example.adapter.input.dto.RequestConsultarContaDTO;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.input.dto.RequestSaqueDTO;
import org.example.adapter.output.dto.*;
import org.example.port.input.OperacaoBancariaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestHeader(required = false) Double saldo){
        ResponseCriacaoDeContaDTO response = operacaoBancariaUseCase.criarContaCorrente(
                RequestCriacaoContaDTO.builder()
                        .titular(titular)
                        .conta(conta)
                        .agencia(agencia)
                        .saldo(saldo != null ? saldo : 0.00)
                        .build()
                );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/criar-conta-poupanca")
    public ResponseEntity<ResponseCriacaoDeContaDTO> criarContaPoupanca(
            @RequestHeader String titular,
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader(required = false) Double saldo){
        ResponseCriacaoDeContaDTO response = operacaoBancariaUseCase.criarContaPoupanca(
                RequestCriacaoContaDTO.builder()
                        .titular(titular)
                        .conta(conta)
                        .agencia(agencia)
                        .saldo(saldo != null ? saldo : 0.00)
                        .build()
                );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/depositar-conta-corrente")
    public ResponseEntity<ResponseDepositoDTO> depositarContaCorrente(
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader double valor){
        ResponseDepositoDTO response = operacaoBancariaUseCase.depositarContaCorrente(
                RequestDepositoDTO.builder()
                        .conta(((Integer) conta).toString())
                        .agencia(((Integer) agencia).toString())
                        .valor(((Double) valor).toString())
                        .build()

        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sacar-conta-corrente")
    public ResponseEntity<ResponseSaqueDTO> sacarContaCorrente(
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader double valor){
        ResponseSaqueDTO response = operacaoBancariaUseCase.sacarContaCorrente(
                RequestSaqueDTO.builder()
                        .conta(((Integer) conta).toString())
                        .agencia(((Integer) agencia).toString())
                        .valor(((Double) valor).toString())
                        .build()

        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/depositar-conta-poupanca")
    public ResponseEntity<ResponseDepositoDTO> depositarContaPoupanca(
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader double valor){
        ResponseDepositoDTO response = operacaoBancariaUseCase.depositarContaPoupanca(
                RequestDepositoDTO.builder()
                        .conta(((Integer) conta).toString())
                        .agencia(((Integer) agencia).toString())
                        .valor(((Double) valor).toString())
                        .build()

        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sacar-conta-poupanca")
    public ResponseEntity<ResponseSaqueDTO> sacarContaPoupanca(
            @RequestHeader int conta,
            @RequestHeader int agencia,
            @RequestHeader double valor){
        ResponseSaqueDTO response = operacaoBancariaUseCase.sacarContaPoupanca(
                RequestSaqueDTO.builder()
                        .conta(((Integer) conta).toString())
                        .agencia(((Integer) agencia).toString())
                        .valor(((Double) valor).toString())
                        .build()

        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultar-conta-corrente/{conta}/{agencia}")
    public ResponseEntity<ResponseConsultaContaDTO> consultarContaCorrente(
            @PathVariable String conta,
            @PathVariable String agencia){
        ResponseConsultaContaDTO response = operacaoBancariaUseCase.consultarContaCorrente(
                RequestConsultarContaDTO.builder()
                        .conta(conta)
                        .agencia(agencia)
                        .build()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultar-conta-poupanca/{conta}/{agencia}")
    public ResponseEntity<ResponseConsultaContaDTO> consultarContaPoupanca(
            @PathVariable String conta,
            @PathVariable String agencia){
        ResponseConsultaContaDTO response = operacaoBancariaUseCase.consultarContaPoupanca(
                RequestConsultarContaDTO.builder()
                        .conta(conta)
                        .agencia(agencia)
                        .build()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar-extrato-conta-corrente/{conta}/{agencia}")
    public ResponseEntity<ResponseListaContasBancariasDTO> listarExtratosContaCorrente(
            @PathVariable String conta,
            @PathVariable String agencia){
        ResponseListaContasBancariasDTO response = operacaoBancariaUseCase.listarExtratosContaCorrente(
                RequestConsultarContaDTO.builder()
                        .conta(conta)
                        .agencia(agencia)
                        .build()
        );
        return ResponseEntity.ok(response);
    }
}
