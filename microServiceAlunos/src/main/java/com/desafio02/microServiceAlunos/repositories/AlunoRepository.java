package com.desafio02.microServiceAlunos.repositories;

import com.desafio02.microServiceAlunos.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
