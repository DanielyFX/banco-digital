package org.example.domain.usecase;

import org.example.adapter.exception.infrastructure.InfrastructureException;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.output.dto.DetailDTO;
import org.example.adapter.output.dto.ResponseCriacaoDeContaDTO;
import org.example.adapter.output.dto.ResponseDepositoDTO;
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

    @Override
    public ResponseCriacaoDeContaDTO criarContaPoupanca(RequestCriacaoContaDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            Conta contaPoupanca = operacaoBancariaStrategy.criarContaPoupanca(conta);
            return ResponseCriacaoDeContaDTO.builder().
                    conta(contaPoupanca.getConta()).
                    agencia(contaPoupanca.getAgencia()).
                    detail(DetailDTO.builder()
                            .codigoRetorno("201")
                            .mensagemRetorno("Conta criada com sucesso")
                            .timestampInicio(timestampInicio.toString())
                            .timestampFim(LocalDateTime.now().toString())
                            .build())
                    .build();

        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }

    }

    @Override
    public ResponseDepositoDTO depositarContaCorrente(RequestDepositoDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaCorrente contaCorrente = (ContaCorrente) operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            contaCorrente.setSaldo(contaCorrente.getSaldo() + Double.parseDouble(conta.valor()));
            operacaoBancariaStrategy.depositarContaCorrente(contaCorrente);
            return ResponseDepositoDTO.builder()
                    .conta(((Integer) contaCorrente.getConta()).toString())
                    .agencia(((Integer)contaCorrente.getAgencia()).toString())
                    .detail(DetailDTO.builder()
                            .codigoRetorno("200")
                            .mensagemRetorno("Deposito realizado com sucesso")
                            .timestampInicio(timestampInicio.toString())
                            .timestampFim(LocalDateTime.now().toString())
                            .build())
                    .build();
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }
    }
}
