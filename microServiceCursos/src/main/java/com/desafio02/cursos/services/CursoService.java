package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }


    public Curso mudarProfessor(String nome, Curso curso) {
        Curso novoCurso = cursoRepository.findByNome(nome);
        novoCurso.setProfessor(curso.getProfessor());
        return novoCurso;
    }
}
