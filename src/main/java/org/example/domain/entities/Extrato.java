package org.example.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "extrato",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tipoTransacao", "hora"})
        })
public class Extrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "transacao")
    private String tipoTransacao;

    private double saldo;
    private double valor;
    private String descricao;
    private String hora;
}
