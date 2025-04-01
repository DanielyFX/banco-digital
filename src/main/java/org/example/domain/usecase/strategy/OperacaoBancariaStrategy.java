package org.example.domain.usecase.strategy;

import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.port.output.ContaCorrenteRepository;
import org.springframework.stereotype.Service;

@Service
public class OperacaoBancariaStrategy{

    private final ContaCorrenteRepository contaCorrenteRepository;

    public OperacaoBancariaStrategy(ContaCorrenteRepository contaCorrenteRepository){
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public Conta criarContaCorrente(RequestCriacaoContaDTO contaCorrenteRequestDTO){
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setTitular(contaCorrenteRequestDTO.titular());
        contaCorrente.setConta(contaCorrenteRequestDTO.conta());
        contaCorrente.setAgencia(contaCorrenteRequestDTO.agencia());
        contaCorrente.setSaldo(contaCorrenteRequestDTO.saldo());
        return contaCorrenteRepository.save(contaCorrente);
    }

}
