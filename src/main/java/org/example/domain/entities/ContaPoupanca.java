package org.example.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "conta_corrente",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"conta", "agencia"})
        })
public class ContaPoupanca extends Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public ContaPoupanca() {
        super("conta_poupanca");
    }
}
