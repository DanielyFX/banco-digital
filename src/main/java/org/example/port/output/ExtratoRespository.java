package org.example.port.output;

import org.example.domain.entities.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ExtratoRespository extends JpaRepository<Extrato, Integer> {
}
