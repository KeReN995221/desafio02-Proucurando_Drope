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

import java.util.List;

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

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Curso não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    @Transactional
    public Curso alterarCurso(Long id, Curso cursoAlterado) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Curso não encontrado")
        );
        curso.setProfessor(cursoAlterado.getProfessor());
        curso.setAtivo(cursoAlterado.isAtivo());
        curso.setNome(cursoAlterado.getNome());
        curso.setAreaConhecimento(cursoAlterado.getAreaConhecimento());
        curso.setQuantidadeHoras(cursoAlterado.getQuantidadeHoras());
        cursoRepository.save(curso);
        return curso;
    }

    public void apagarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Curso não encontrado")
        );
        cursoRepository.delete(curso);
    }
}
