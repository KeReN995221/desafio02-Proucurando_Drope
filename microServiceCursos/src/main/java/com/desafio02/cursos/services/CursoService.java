package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.repositories.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso mudarProfessor(Long id, Curso curso) {
        Curso novoCurso = cursoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        );
        novoCurso.setProfessor(curso.getProfessor());
        cursoRepository.save(novoCurso);
        return novoCurso;
    }

    public Curso desabilitarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        );
        curso.setAtivo(false);
        cursoRepository.save(curso);
        return curso;
    }

    @Transactional
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso não encontrado", id))
        );
    }

    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }
}
