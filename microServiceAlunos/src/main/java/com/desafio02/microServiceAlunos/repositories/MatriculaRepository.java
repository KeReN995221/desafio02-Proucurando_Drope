package com.desafio02.microServiceAlunos.repositories;

import com.desafio02.microServiceAlunos.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByIdAlunoAndIdCurso(Long idAluno, Long idCurso);
}
