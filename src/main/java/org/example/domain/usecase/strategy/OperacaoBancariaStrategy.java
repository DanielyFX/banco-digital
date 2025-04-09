package org.example.domain.usecase.strategy;

import org.example.adapter.input.dto.RequestConsultarContaDTO;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.ContaPoupanca;
import org.example.domain.entities.Extrato;
import org.example.port.output.ContaCorrenteRepository;
import org.example.port.output.ContaPoupancaRepository;
import org.example.port.output.ExtratoRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OperacaoBancariaStrategy{

    private final ContaCorrenteRepository contaCorrenteRepository;
    private final ContaPoupancaRepository contaPoupancaRepository;
    private final ExtratoRespository extratoRespository;

    public OperacaoBancariaStrategy(ContaCorrenteRepository contaCorrenteRepository, ContaPoupancaRepository contaPoupancaRepository,
                                    ExtratoRespository extratoRespository){
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.contaPoupancaRepository = contaPoupancaRepository;
        this.extratoRespository = extratoRespository;
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

    public Conta buscarContaPoupanca(int conta, int agencia){
        return contaPoupancaRepository.findByContaAndAgencia(conta, agencia);
    }

    public Conta depositarContaCorrente(ContaCorrente contaCorrente, double valor){
        contaCorrente.setSaldo(contaCorrente.getSaldo() + valor);
        return contaCorrenteRepository.save(contaCorrente);
    }

    public Conta depositarContaPoupanca(ContaPoupanca contaPoupanca, double valor){
        contaPoupanca.setSaldo(contaPoupanca.getSaldo() + valor);
        return contaPoupancaRepository.save(contaPoupanca);
    }

    public Conta sacarContaCorrente(ContaCorrente contaCorrente, double valor){
        contaCorrente.setSaldo(contaCorrente.getSaldo() - valor);
        return contaCorrenteRepository.save(contaCorrente);
    }

    public Conta sacarContaPoupanca(ContaPoupanca contaPoupanca, double valor){
        contaPoupanca.setSaldo(contaPoupanca.getSaldo() - valor);
        return contaPoupancaRepository.save(contaPoupanca);
    }

    public Extrato gravarExtrato(Conta conta, String tipoTransacao, String descricao, double valor){
        Extrato extrato = new Extrato();
        extrato.setTipoTransacao(tipoTransacao);
        extrato.setValor(valor);
        extrato.setDescricao(descricao);
        extrato.setHora(LocalDate.now().toString());
        extratoRespository.save(extrato);
        return extrato;
    }

    public List<Extrato> listarExtratos(Conta conta){
        return conta.getExtratos();
    }

}
