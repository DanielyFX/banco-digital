package org.example.port.output;

import org.example.domain.entities.Conta;
import org.example.domain.entities.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
    ContaCorrente findByContaAndAgencia(int conta, int agencia);
}
