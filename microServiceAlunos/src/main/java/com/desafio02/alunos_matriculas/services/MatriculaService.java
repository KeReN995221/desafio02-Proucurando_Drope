package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.client.CursoClient;
import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.repositories.MatriculaRepository;
import com.desafio02.alunos_matriculas.web.controller.AlunoController;
import com.desafio02.alunos_matriculas.web.dto.CursoDto;
import com.desafio02.alunos_matriculas.web.dto.MatriculaDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    @Autowired
    private CursoClient cursoClient;
    @Autowired
    private AlunoController alunoController;

    @Transactional
    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Matricula id=%s não encontrado", id))
        );
    }
    @Transactional
    public Matricula salvar (MatriculaDto matriculaDto) {
        Matricula matricula = new Matricula();
        try {
            CursoDto curso = cursoClient.getCursoById(matriculaDto.getIdCurso());
            Aluno aluno = alunoController.getById(matriculaDto.getIdAluno()).getBody();
            if (!curso.isAtivo()) {
                throw new RuntimeException("O curso não está ativo e não pode ser matriculado.");
            }
            if (!aluno.isAtivo()) {
                throw new RuntimeException("O aluno não está ativo e não pode se matricular.");
            }
            matricula.setIdCurso(curso.getId());
            matricula.setIdAluno(aluno.getId());
            matricula.setAtivo(true);
        }
        catch (RuntimeException x) {
            throw new RuntimeException("Matricula inválida.");
        }
        return matriculaRepository.save(matricula);
    }
    @Transactional
    public Matricula inativarMatricula(Long id) {
        Matricula matricula = buscarPorId(id);
        matricula.setAtivo(false);
        return matricula;
    }

    @Transactional
    public void apagarMatricula(Long id) {
        matriculaRepository.delete(buscarPorId(id));
    }
}