package org.example.port.input;

import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.output.dto.ResponseCriacaoDeContaDTO;
import org.example.adapter.output.dto.ResponseDepositoDTO;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OperacaoBancariaUseCase {
    ResponseCriacaoDeContaDTO criarContaCorrente(RequestCriacaoContaDTO conta);
    ResponseCriacaoDeContaDTO criarContaPoupanca(RequestCriacaoContaDTO conta);
    ResponseDepositoDTO depositarContaCorrente(RequestDepositoDTO conta);

}
