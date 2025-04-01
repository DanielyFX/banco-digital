package org.example.port.output;

import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Integer> {
}
