package com.desafio02.cursos.repositories;

import com.desafio02.cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}