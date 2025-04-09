package org.example.domain.usecase.strategy;

import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.ContaPoupanca;
import org.example.port.output.ContaCorrenteRepository;
import org.example.port.output.ContaPoupancaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperacaoBancariaStrategy{

    private final ContaCorrenteRepository contaCorrenteRepository;
    private final ContaPoupancaRepository contaPoupancaRepository;

    public OperacaoBancariaStrategy(ContaCorrenteRepository contaCorrenteRepository, ContaPoupancaRepository contaPoupancaRepository){
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.contaPoupancaRepository = contaPoupancaRepository;
    }

    public Conta criarContaCorrente(RequestCriacaoContaDTO contaCorrenteRequestDTO){
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setTitular(contaCorrenteRequestDTO.titular());
        contaCorrente.setConta(contaCorrenteRequestDTO.conta());
        contaCorrente.setAgencia(contaCorrenteRequestDTO.agencia());
        contaCorrente.setSaldo(contaCorrenteRequestDTO.saldo());
        return contaCorrenteRepository.save(contaCorrente);
    }

    public Conta criarContaPoupanca(RequestCriacaoContaDTO requestCriacaoContaDTO){
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setTitular(requestCriacaoContaDTO.titular());
        contaPoupanca.setConta(requestCriacaoContaDTO.conta());
        contaPoupanca.setAgencia(requestCriacaoContaDTO.agencia());
        contaPoupanca.setSaldo(requestCriacaoContaDTO.saldo());
        return contaPoupancaRepository.save(contaPoupanca);
    }

    public Conta buscarContaCorrente(int conta, int agencia){
        return contaCorrenteRepository.findByContaAndAgencia(conta, agencia);
    }

    public Conta depositarContaCorrente(ContaCorrente contaCorrente){
        return contaCorrenteRepository.save(contaCorrente);

    }

}
