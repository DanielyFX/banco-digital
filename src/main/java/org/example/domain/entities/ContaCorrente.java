package org.example.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "conta_corrente",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"conta", "agencia"})
        })
public class ContaCorrente extends Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public ContaCorrente() {
        super("conta_corrente");
    }
}
