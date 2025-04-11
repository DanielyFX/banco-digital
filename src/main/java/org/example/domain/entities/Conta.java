package org.example.domain.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public class Conta {

    public Conta(String TIPO_CONTA){
        this.extratos = new ArrayList<>();
        this.TIPO_CONTA = TIPO_CONTA;
    }

    @NotNull(message = "Titular não pode ser nulo")
    private String titular;

    @NotNull(message = "Conta não pode ser nula")
    private int conta;

    @NotNull(message = "Agência não pode ser nula")
    private int agencia;


    private double saldo;
    private final String TIPO_CONTA;

    @OneToMany
    private List<Extrato> extratos;
}
