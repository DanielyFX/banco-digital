package org.example.domain.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@MappedSuperclass
public class Conta {

    private String titular;
    private int conta;
    private int agencia;
    private double saldo;
    private final String TIPO_CONTA;

    @OneToMany
    private List<Extrato> extratos;
}
