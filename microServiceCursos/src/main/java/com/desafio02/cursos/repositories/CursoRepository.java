package com.desafio02.cursos.repositories;

import com.desafio02.cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}