package org.example.domain.usecase;

import org.example.adapter.exception.infrastructure.InfrastructureException;
import org.example.adapter.input.dto.*;
import org.example.adapter.output.dto.*;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.ContaPoupanca;
import org.example.domain.entities.Extrato;
import org.example.domain.usecase.strategy.OperacaoBancariaStrategy;
import org.example.port.input.OperacaoBancariaUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            Extrato extrato = operacaoBancariaStrategy.gravarExtrato(contaCorrente, "Deposito", "Inserção de valor na conta", Double.parseDouble(conta.valor()));
            //if (contaCorrente.getExtratos() == null) {
            //    contaCorrente.setExtratos(new ArrayList<>());
            //}
            List<Extrato> extratos = contaCorrente.getExtratos();
            extratos.add(extrato);
            contaCorrente.setExtratos(extratos);
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
            Extrato extrato = operacaoBancariaStrategy.gravarExtrato(contaCorrente, "Saque", "Retirado de valor da conta", Double.parseDouble(conta.valor()));
            if(contaCorrente.getSaldo() < Double.parseDouble(conta.valor())){
                throw new InfrastructureException("202", "Saldo insuficiente");
            }
            //if (contaCorrente.getExtratos() == null) {
            //    contaCorrente.setExtratos(new ArrayList<>());
            //}
            List<Extrato> extratos = contaCorrente.getExtratos();
            extratos.add(extrato);
            contaCorrente.setExtratos(extratos);
            operacaoBancariaStrategy.sacarContaCorrente(contaCorrente, Double.parseDouble(conta.valor()));

            return ResponseSaqueDTO.builder()
                    .conta(((Integer) contaCorrente.getConta()).toString())
                    .agencia(((Integer)contaCorrente.getAgencia()).toString())
                    .saldo(contaCorrente.getSaldo())
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
            ContaPoupanca contaPoupanca = (ContaPoupanca) operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            if(contaPoupanca.getSaldo() < Double.parseDouble(conta.valor())){
                throw new InfrastructureException("202", "Saldo insuficiente");
            }
            Extrato extrato = operacaoBancariaStrategy.gravarExtrato(contaPoupanca, "Deposito", "Inserção de valor na conta", Double.parseDouble(conta.valor()));
            //if (contaPoupanca.getExtratos() == null) {
            //    contaPoupanca.setExtratos(new ArrayList<>());
            //}
            List<Extrato> extratos = contaPoupanca.getExtratos();
            extratos.add(extrato);
            contaPoupanca.setExtratos(extratos);
            operacaoBancariaStrategy.depositarContaPoupanca(contaPoupanca, Double.parseDouble(conta.valor()));
            return ResponseDepositoDTO.builder()
                    .conta(((Integer) contaPoupanca.getConta()).toString())
                    .agencia(((Integer)contaPoupanca.getAgencia()).toString())
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
            Extrato extrato = operacaoBancariaStrategy.gravarExtrato(contaPoupanca, "Saque", "Retirado de valor da conta", Double.parseDouble(conta.valor()));
            //if (contaPoupanca.getExtratos() == null) {
            //    contaPoupanca.setExtratos(new ArrayList<>());
            //}
            List<Extrato> extratos = contaPoupanca.getExtratos();
            extratos.add(extrato);
            contaPoupanca.setExtratos(extratos);
            operacaoBancariaStrategy.sacarContaPoupanca(contaPoupanca, Double.parseDouble(conta.valor()));
            return ResponseSaqueDTO.builder()
                    .conta(((Integer) contaPoupanca.getConta()).toString())
                    .agencia(((Integer)contaPoupanca.getAgencia()).toString())
                    .saldo(contaPoupanca.getSaldo())
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
    public ResponseListaExtratosDTO listarExtratosContaCorrente(RequestConsultarContaDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaCorrente contaCorrente = (ContaCorrente) operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            return ResponseListaExtratosDTO.builder()
                    .conta(((Integer)contaCorrente.getConta()).toString())
                    .agencia(((Integer)contaCorrente.getAgencia()).toString())
                    .extratos(operacaoBancariaStrategy.listarExtratos(contaCorrente).stream()
                    .map(extrato -> ResponseExtratoDTO.builder()
                            .saldo(((Double)extrato.getSaldo()).toString())
                            .transacao(extrato.getTipoTransacao())
                            .valor(((Double) extrato.getValor()).toString())
                            .descricao(extrato.getDescricao())
                            .data(extrato.getHora())
                            .build())
                    .toList())
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
    public ResponseListaExtratosDTO listarExtratosContaPoupanca(RequestConsultarContaDTO conta) {
        LocalDateTime timestampInicio = LocalDateTime.now();
        try{
            ContaPoupanca contaPoupanca = (ContaPoupanca) operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(conta.conta()), Integer.parseInt(conta.agencia()));
            return ResponseListaExtratosDTO.builder()
                    .conta(((Integer)contaPoupanca.getConta()).toString())
                    .agencia(((Integer)contaPoupanca.getAgencia()).toString())
                    .extratos(operacaoBancariaStrategy.listarExtratos(contaPoupanca).stream()
                            .map(extrato -> ResponseExtratoDTO.builder()
                                    .saldo(((Double)extrato.getSaldo()).toString())
                                    .transacao(extrato.getTipoTransacao())
                                    .valor(((Double) extrato.getValor()).toString())
                                    .descricao(extrato.getDescricao())
                                    .data(extrato.getHora())
                                    .build())
                            .toList())
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
    public ResponseTransferenciaDTO transferir(RequestTransferenciaDTO requestTransferenciaDTO){
        LocalDateTime timestampInicio = LocalDateTime.now();
        Conta contaOrigem;
        Conta contaDestino;
        try{
            contaOrigem =  operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(requestTransferenciaDTO.contaOrigem()), Integer.parseInt(requestTransferenciaDTO.agenciaOrigem()));
            if(contaOrigem == null){
                contaOrigem = operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(requestTransferenciaDTO.contaOrigem()), Integer.parseInt(requestTransferenciaDTO.agenciaOrigem()));
            }
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }

        try{
            contaDestino = operacaoBancariaStrategy.buscarContaCorrente(Integer.parseInt(requestTransferenciaDTO.contaDestino()), Integer.parseInt(requestTransferenciaDTO.agenciaDestino()));
            if(contaDestino == null){
                contaDestino = operacaoBancariaStrategy.buscarContaPoupanca(Integer.parseInt(requestTransferenciaDTO.contaDestino()), Integer.parseInt(requestTransferenciaDTO.agenciaDestino()));
            }
        } catch (Exception ex){
            throw new InfrastructureException("202", ex.getMessage());
        }

        if(contaOrigem.getSaldo() > (Double.parseDouble(requestTransferenciaDTO.valor()))){
            try{
                Extrato extratoContaOrigem = operacaoBancariaStrategy.gravarExtrato(contaOrigem, "Transferencia", "Transferido de valor da conta", Double.parseDouble(requestTransferenciaDTO.valor()));
                contaOrigem.getExtratos().add(extratoContaOrigem);
                Extrato extratoContaDestino = operacaoBancariaStrategy.gravarExtrato(contaDestino, "Transferencia", "Recebido de valor da conta", Double.parseDouble(requestTransferenciaDTO.valor()));
                contaDestino.getExtratos().add(extratoContaDestino);
                operacaoBancariaStrategy.transferir(contaOrigem, contaDestino, Double.parseDouble(requestTransferenciaDTO.valor()));
            }catch (Exception ex){
                throw new InfrastructureException("202", ex.getMessage());
            }
        }

        return ResponseTransferenciaDTO.builder()
                .agenciaOrigem(((Integer)contaOrigem.getAgencia()).toString())
                .contaOrigem(((Integer)contaOrigem.getConta()).toString())
                .agenciaDestino(((Integer)contaDestino.getAgencia()).toString())
                .contaDestino(((Integer)contaDestino.getConta()).toString())
                .valor(requestTransferenciaDTO.valor())
                .detail(DetailDTO.builder()
                        .codigoRetorno("200")
                        .mensagemRetorno("Transferencia realizada com sucesso")
                        .timestampInicio(timestampInicio.toString())
                        .timestampFim(LocalDateTime.now().toString())
                        .build())
                .build();

    }
}
