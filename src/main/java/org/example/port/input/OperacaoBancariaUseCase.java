package org.example.port.input;

import org.example.adapter.input.dto.RequestConsultarContaDTO;
import org.example.adapter.input.dto.RequestCriacaoContaDTO;
import org.example.adapter.input.dto.RequestDepositoDTO;
import org.example.adapter.input.dto.RequestSaqueDTO;
import org.example.adapter.output.dto.*;


public interface OperacaoBancariaUseCase {
    ResponseCriacaoDeContaDTO criarContaCorrente(RequestCriacaoContaDTO conta);
    ResponseCriacaoDeContaDTO criarContaPoupanca(RequestCriacaoContaDTO conta);
    ResponseDepositoDTO depositarContaCorrente(RequestDepositoDTO conta);
    ResponseDepositoDTO depositarContaPoupanca(RequestDepositoDTO conta);
    ResponseConsultaContaDTO consultarContaCorrente(RequestConsultarContaDTO conta);
    ResponseConsultaContaDTO consultarContaPoupanca(RequestConsultarContaDTO conta);
    ResponseSaqueDTO sacarContaCorrente(RequestSaqueDTO conta);
    ResponseSaqueDTO sacarContaPoupanca(RequestSaqueDTO conta);
    ResponseListaExtratosDTO listarExtratosContaCorrente(RequestConsultarContaDTO conta);
    ResponseListaExtratosDTO listarExtratosContaPoupanca(RequestConsultarContaDTO conta);


}
