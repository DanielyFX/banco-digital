package org.example.domain.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Conta {

    private String titular;
    private int conta;
    private int agencia;
    private double saldo;
    private final String TIPO_CONTA;
}
