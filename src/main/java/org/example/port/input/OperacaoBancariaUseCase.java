package org.example.port.input;

import org.example.adapter.input.dto.RequestConsultarContaDTO;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.input.dto.RequestSaqueDTO;
import org.example.adapter.output.dto.*;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OperacaoBancariaUseCase {
    ResponseCriacaoDeContaDTO criarContaCorrente(RequestCriacaoContaDTO conta);
    ResponseCriacaoDeContaDTO criarContaPoupanca(RequestCriacaoContaDTO conta);
    ResponseDepositoDTO depositarContaCorrente(RequestDepositoDTO conta);
    ResponseDepositoDTO depositarContaPoupanca(RequestDepositoDTO conta);
    ResponseConsultaContaDTO consultarContaCorrente(RequestConsultarContaDTO conta);
    ResponseConsultaContaDTO consultarContaPoupanca(RequestConsultarContaDTO conta);
    ResponseSaqueDTO sacarContaCorrente(RequestSaqueDTO conta);
    ResponseSaqueDTO sacarContaPoupanca(RequestSaqueDTO conta);
    ResponseListaContasBancariasDTO listarExtratosContaCorrente(RequestConsultarContaDTO conta);

}
