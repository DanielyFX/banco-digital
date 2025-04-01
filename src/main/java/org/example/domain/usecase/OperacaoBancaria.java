package org.example.domain.usecase;

import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.output.dto.DetailDTO;
import org.example.adapter.output.dto.ResponseCriacaoDeContaDTO;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.usecase.strategy.OperacaoBancariaStrategy;
import org.example.port.input.OperacaoBancariaUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperacaoBancaria implements OperacaoBancariaUseCase {

    private final OperacaoBancariaStrategy operacaoBancariaStrategy;

    public OperacaoBancaria(OperacaoBancariaStrategy operacaoBancariaStrategy) {
        this.operacaoBancariaStrategy = operacaoBancariaStrategy;
    }

    @Override
    public ResponseCriacaoDeContaDTO criarContaCorrente(RequestCriacaoContaDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        Conta contaCorrente = operacaoBancariaStrategy.criarContaCorrente(conta);
        return ResponseCriacaoDeContaDTO.builder().
                conta(contaCorrente.getConta()).
                agencia(contaCorrente.getAgencia()).
                detail(DetailDTO.builder()
                        .codigoRetorno("201")
                        .mensagemRetorno("Conta criada com sucesso")
                        .timestampInicio(timestampInicio.toString())
                        .timestampFim(LocalDateTime.now().toString())
                        .build())
                .build();
    }
}
