package com.desafio02.alunos_matriculas.repositories;

import com.desafio02.alunos_matriculas.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
