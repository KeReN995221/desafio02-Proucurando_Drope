package com.desafio02.microServiceAlunos.repositories;

import com.desafio02.microServiceAlunos.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
