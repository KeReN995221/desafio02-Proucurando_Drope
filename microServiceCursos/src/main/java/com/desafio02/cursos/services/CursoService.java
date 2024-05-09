package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.excpetions.EntityNotFoundException;
import com.desafio02.cursos.excpetions.NameUniqueViolationException;
import com.desafio02.cursos.excpetions.UnableCourseException;
import com.desafio02.cursos.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public Curso salvar(Curso curso) {
        try {
            return cursoRepository.save(curso);
        } catch (DataIntegrityViolationException ex) {
            throw new NameUniqueViolationException("O nome do curso deve ser único");
        }
    }

    @Transactional
    public Curso mudarProfessor(Long id, Curso curso) {
        Curso novoCurso = cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Curso não encontrado")
        );
        novoCurso.setProfessor(curso.getProfessor());
        cursoRepository.save(novoCurso);
        return novoCurso;
    }

    @Transactional
    public Curso desabilitarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Curso não encontrado")
        );
        if (curso.isAtivo()) curso.setAtivo(false);
        else throw new UnableCourseException("O curso já esta desabilitado");
        cursoRepository.save(curso);
        return curso;
    }
}
