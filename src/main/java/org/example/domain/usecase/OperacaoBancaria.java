package org.example.domain.usecase;

import org.example.adapter.exception.infrastructure.InfrastructureException;
import org.example.adapter.input.dto.RequestConsultarContaDTO;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.input.dto.RequestSaqueDTO;
import org.example.adapter.output.dto.*;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.ContaPoupanca;
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
    public ResponseConsultaContaDTO consultarContaCorrente(RequestConsultarContaDTO consultarContaDTO){
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaCorrente contaCorrente = (ContaCorrente) operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(consultarContaDTO.conta()), Integer.parseInt(consultarContaDTO.agencia()));
            return ResponseConsultaContaDTO.builder()
                    .titular(contaCorrente.getTitular())
                    .conta(contaCorrente.getConta())
                    .agencia(contaCorrente.getAgencia())
                    .saldo(contaCorrente.getSaldo())
                    .detail(DetailDTO.builder()
                            .codigoRetorno("200")
                            .mensagemRetorno("Consulta realizada com sucesso")
                            .timestampInicio(timestampInicio.toString())
                            .timestampFim(LocalDateTime.now().toString())
                            .build())
                    .build();
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }
    }

    @Override
    public ResponseConsultaContaDTO consultarContaPoupanca(RequestConsultarContaDTO consultarContaDTO){
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaPoupanca contaPoupanca = (ContaPoupanca) operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(consultarContaDTO.conta()), Integer.parseInt(consultarContaDTO.agencia()));
            return ResponseConsultaContaDTO.builder()
                    .titular(contaPoupanca.getTitular())
                    .conta(contaPoupanca.getConta())
                    .agencia(contaPoupanca.getAgencia())
                    .saldo(contaPoupanca.getSaldo())
                    .detail(DetailDTO.builder()
                            .codigoRetorno("200")
                            .mensagemRetorno("Consulta realizada com sucesso")
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
            operacaoBancariaStrategy.depositarContaCorrente(contaCorrente, Double.parseDouble(conta.valor()));
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

    @Override
    public ResponseSaqueDTO sacarContaCorrente(RequestSaqueDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaCorrente contaCorrente = (ContaCorrente) operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            operacaoBancariaStrategy.sacarContaCorrente(contaCorrente, Double.parseDouble(conta.valor()));
            return ResponseSaqueDTO.builder()
                    .conta(((Integer) contaCorrente.getConta()).toString())
                    .agencia(((Integer)contaCorrente.getAgencia()).toString())
                    .valorSacado(contaCorrente.getSaldo())
                    .detail(DetailDTO.builder()
                            .codigoRetorno("200")
                            .mensagemRetorno("Saque realizado com sucesso")
                            .timestampInicio(timestampInicio.toString())
                            .timestampFim(LocalDateTime.now().toString())
                            .build())
                    .build();
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }
    }

    @Override
    public ResponseDepositoDTO depositarContaPoupanca(RequestDepositoDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaPoupanca contaPopupanca = (ContaPoupanca) operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            if(contaPopupanca.getSaldo() < Double.parseDouble(conta.valor())){
                throw new InfrastructureException("202", "Saldo insuficiente");
            }
            operacaoBancariaStrategy.depositarContaPoupanca(contaPopupanca, Double.parseDouble(conta.valor()));
            return ResponseDepositoDTO.builder()
                    .conta(((Integer) contaPopupanca.getConta()).toString())
                    .agencia(((Integer)contaPopupanca.getAgencia()).toString())
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

    @Override
    public ResponseSaqueDTO sacarContaPoupanca(RequestSaqueDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaPoupanca contaPoupanca = (ContaPoupanca) operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            if(contaPoupanca.getSaldo() < Double.parseDouble(conta.valor())){
                throw new InfrastructureException("202", "Saldo insuficiente");
            }
            operacaoBancariaStrategy.sacarContaPoupanca(contaPoupanca, Double.parseDouble(conta.valor()));
            return ResponseSaqueDTO.builder()
                    .conta(((Integer) contaPoupanca.getConta()).toString())
                    .agencia(((Integer)contaPoupanca.getAgencia()).toString())
                    .valorSacado(contaPoupanca.getSaldo())
                    .detail(DetailDTO.builder()
                            .codigoRetorno("200")
                            .mensagemRetorno("Saque realizado com sucesso")
                            .timestampInicio(timestampInicio.toString())
                            .timestampFim(LocalDateTime.now().toString())
                            .build())
                    .build();
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }
    }
}
