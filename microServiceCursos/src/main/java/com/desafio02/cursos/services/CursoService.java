package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.excpetions.*;
import com.desafio02.cursos.repositories.CursoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso não encontrado")));
    }

    @Transactional
    public Curso salvar(@Valid Curso curso) {
        try {
            curso.setTotalAlunos(0);
            return cursoRepository.save(curso);
        } catch (DataIntegrityViolationException ex) {
            throw new NameUniqueViolationException("O nome do curso deve ser único");
        }
    }

    @Transactional
    public Curso mudarProfessor(Long id, Curso curso) {
        Curso novoCurso = buscarPorId(id);
        novoCurso.setProfessor(curso.getProfessor());
        cursoRepository.save(novoCurso);
        return novoCurso;
    }

    @Transactional
    public Curso desabilitarCurso(Long id) {
        Curso curso = buscarPorId(id);
        if (curso.isAtivo()) curso.setAtivo(false);
        else throw new UnableCourseException("O curso já está desabilitado");
        cursoRepository.save(curso);
        return curso;
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarTodos() {
        List<Curso> cursos = cursoRepository.findAll();
        if (cursos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum curso encontrado");
        }
        return cursos;
    }

    @Transactional(readOnly = true)
    public Integer buscarTotalAlunos(Long id) {
        Curso curso = buscarPorId(id);
        return curso.getTotalAlunos();
    }

    @Transactional
    public void aumentarTotalMatriculas(Long id) {
        Curso curso = buscarPorId(id);
        curso.setTotalAlunos(curso.getTotalAlunos()+1);
    }

    @Transactional
    public void diminuirTotalMatriculas(Long id) {
        Curso curso = buscarPorId(id);
        curso.setTotalAlunos(curso.getTotalAlunos()-1);
    }
}
