package org.example.domain.usecase;

import org.example.adapter.input.dto.*;
import org.example.adapter.output.dto.*;
import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.example.domain.entities.ContaPoupanca;
import org.example.domain.entities.Extrato;
import org.example.domain.usecase.strategy.OperacaoBancariaStrategy;
import org.example.port.output.ContaCorrenteRepository;
import org.example.port.output.ContaPoupancaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OperacaoBancariaTest {

    @Mock
    private OperacaoBancariaStrategy operacaoBancariaStrategy;

    @InjectMocks
    private OperacaoBancaria operacaoBancaria;

    // Test methods go here

    @Test
    public void testCriarContaCorrente() {
        Conta response = new ContaCorrente();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.criarContaCorrente(Mockito.any(RequestCriacaoContaDTO.class)))
                .thenReturn(response);

        ResponseCriacaoDeContaDTO conta = operacaoBancaria.criarContaCorrente(
                RequestCriacaoContaDTO.builder()
                        .titular("John Doe")
                        .conta(123)
                        .agencia(456)
                        .saldo(1000.0)
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).criarContaCorrente(Mockito.any(RequestCriacaoContaDTO.class));

        assertEquals(ResponseCriacaoDeContaDTO.class, conta.getClass());

    }

    @Test
    public void testCriarContaPoupanca() {
        Conta response = new ContaPoupanca();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.criarContaPoupanca(RequestCriacaoContaDTO.builder()
                        .conta(123)
                        .agencia(456)
                        .titular("John Doe")
                        .saldo(1000.0)
                        .build()))
                .thenReturn(response);

        ResponseCriacaoDeContaDTO conta = operacaoBancaria.criarContaPoupanca(
                RequestCriacaoContaDTO.builder()
                        .titular("John Doe")
                        .conta(123)
                        .agencia(456)
                        .saldo(1000.0)
                        .build()
        );
        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).criarContaPoupanca(Mockito.any(RequestCriacaoContaDTO.class));
        assertEquals(ResponseCriacaoDeContaDTO.class, conta.getClass());
    }

    @Test
    public void testeConsultarContaCorrente(){
        Conta response = new ContaCorrente();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.buscarContaCorrente(123, 456))
                .thenReturn(response);


        ResponseConsultaContaDTO conta = operacaoBancaria.consultarContaCorrente(RequestConsultarContaDTO.builder()
                .conta("123")
                .agencia("456")
                .build());

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(123, 456);

        assertEquals(ResponseConsultaContaDTO.class, conta.getClass());
    }

    @Test
    public void testeConsultarContaPoupanca() {
        Conta response = new ContaPoupanca();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.buscarContaPoupanca(123, 456))
                .thenReturn(response);

        ResponseConsultaContaDTO conta = operacaoBancaria.consultarContaPoupanca(RequestConsultarContaDTO.builder()
                .conta("123")
                .agencia("456")
                .build());

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaPoupanca(123, 456);

        assertEquals(ResponseConsultaContaDTO.class, conta.getClass());
    }

    @Test
    public void testDepositarContaCorrente() {
        ContaCorrente response = new ContaCorrente();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.buscarContaCorrente(123, 456))
                .thenReturn(response);


        ResponseDepositoDTO conta = operacaoBancaria.depositarContaCorrente(
                RequestDepositoDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .valor("1000.0")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(123, 456);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(response, "Deposito", "Inserção de valor na conta", 1_000);
        inOrder.verify(operacaoBancariaStrategy).depositarContaCorrente(response, 1_000);

        assertEquals(ResponseDepositoDTO.class, conta.getClass());
    }

    @Test
    public void testDepositarContaPoupanca() {
        ContaPoupanca response = new ContaPoupanca();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(1000.0);

        Mockito.when(operacaoBancariaStrategy.buscarContaPoupanca(123, 456))
                .thenReturn(response);

        ResponseDepositoDTO conta = operacaoBancaria.depositarContaPoupanca(
                RequestDepositoDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .valor("1000.0")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaPoupanca(123, 456);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(response, "Deposito", "Inserção de valor na conta", 1_000);
        inOrder.verify(operacaoBancariaStrategy).depositarContaPoupanca(response, 1_000);

        assertEquals(ResponseDepositoDTO.class, conta.getClass());

    }

    @Test
    public void testSacarContaCorrente(){
        ContaCorrente response = new ContaCorrente();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(500);

        Mockito.when(operacaoBancariaStrategy.buscarContaCorrente(123, 456))
                .thenReturn(response);

        ResponseSaqueDTO responseSaqueDTO = operacaoBancaria.sacarContaCorrente(
                RequestSaqueDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .valor("100.0")
                        .build()
        );
        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(123, 456);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(response, "Saque", "Retirado de valor da conta", 100);
        inOrder.verify(operacaoBancariaStrategy).sacarContaCorrente(response, 100.0);
        assertEquals(ResponseSaqueDTO.class, responseSaqueDTO.getClass());
    }

    @Test
    public void testSacarContaPoupanca(){
        ContaPoupanca response = new ContaPoupanca();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(500);

        Mockito.when(operacaoBancariaStrategy.buscarContaPoupanca(123, 456))
                .thenReturn(response);

        ResponseSaqueDTO responseSaqueDTO = operacaoBancaria.sacarContaPoupanca(
                RequestSaqueDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .valor("100.0")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaPoupanca(123, 456);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(response, "Saque", "Retirado de valor da conta", 100);
        inOrder.verify(operacaoBancariaStrategy).sacarContaPoupanca(response, 100.0);
        assertEquals(ResponseSaqueDTO.class, responseSaqueDTO.getClass());
    }

    @Test
    public void testListarExtratosContaCorrente(){

        Conta response = new ContaCorrente();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(500);
        response.setExtratos(new ArrayList<>());
        Extrato extrato = new Extrato();
        extrato.setHora("2023-10-01");
        extrato.setTipoTransacao("Saque");
        extrato.setValor(100.0);
        extrato.setDescricao("Saque realizado");
        extrato.setSaldo(400.0);
        response.getExtratos().add(extrato);
        Mockito.when(operacaoBancariaStrategy.buscarContaCorrente(123, 456))
                .thenReturn(response);

        ResponseListaExtratosDTO responseSaqueDTO = operacaoBancaria.listarExtratosContaCorrente(
                RequestConsultarContaDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(123, 456);
        inOrder.verify(operacaoBancariaStrategy).listarExtratos(response);

        assertEquals(ResponseListaExtratosDTO.class, responseSaqueDTO.getClass());
    }

    @Test
    public void testListarExtratosContaPoupanca(){

        Conta response = new ContaPoupanca();
        response.setConta(123);
        response.setAgencia(456);
        response.setTitular("John Doe");
        response.setSaldo(500);
        response.setExtratos(new ArrayList<>());
        Extrato extrato = new Extrato();
        extrato.setHora("2023-10-01");
        extrato.setTipoTransacao("Saque");
        extrato.setValor(100.0);
        extrato.setDescricao("Saque realizado");
        extrato.setSaldo(400.0);
        response.getExtratos().add(extrato);
        Mockito.when(operacaoBancariaStrategy.buscarContaPoupanca(123, 456))
                .thenReturn(response);

        ResponseListaExtratosDTO responseSaqueDTO = operacaoBancaria.listarExtratosContaPoupanca(
                RequestConsultarContaDTO.builder()
                        .conta("123")
                        .agencia("456")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaPoupanca(123, 456);
        inOrder.verify(operacaoBancariaStrategy).listarExtratos(response);

        assertEquals(ResponseListaExtratosDTO.class, responseSaqueDTO.getClass());
    }

    @Test
    public void testTransferir() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setConta(123);
        contaCorrente.setAgencia(456);
        contaCorrente.setTitular("John Doe");
        contaCorrente.setSaldo(1000.0);

        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setConta(789);
        contaPoupanca.setAgencia(101);
        contaPoupanca.setTitular("Jane Doe");
        contaPoupanca.setSaldo(500.0);

        Mockito.when(operacaoBancariaStrategy.buscarContaCorrente(123, 456))
                .thenReturn(contaCorrente);

        Mockito.when(operacaoBancariaStrategy.buscarContaPoupanca(789, 101))
                .thenReturn(contaPoupanca);

        ResponseTransferenciaDTO responseTransferenciaDTO = operacaoBancaria.transferir(
                RequestTransferenciaDTO.builder()
                        .contaOrigem("123")
                        .agenciaOrigem("456")
                        .contaDestino("789")
                        .agenciaDestino("101")
                        .valor("200.0")
                        .build()
        );

        InOrder inOrder = Mockito.inOrder(operacaoBancariaStrategy);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(123, 456);
        inOrder.verify(operacaoBancariaStrategy).buscarContaCorrente(789, 101);
        inOrder.verify(operacaoBancariaStrategy).buscarContaPoupanca(789, 101);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(contaCorrente, "Transferencia", "Transferido de valor da conta", 200);
        inOrder.verify(operacaoBancariaStrategy).gravarExtrato(contaPoupanca, "Transferencia", "Recebido de valor da conta", 200);
        inOrder.verify(operacaoBancariaStrategy).transferir(contaCorrente, contaPoupanca, 200.0);

        assertEquals(ResponseTransferenciaDTO.class, responseTransferenciaDTO.getClass());
    }

}
