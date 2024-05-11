package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.client.CursoClient;
import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.repositories.AlunoRepository;
import com.desafio02.alunos_matriculas.repositories.MatriculaRepository;
import com.desafio02.alunos_matriculas.web.controller.AlunoController;
import com.desafio02.alunos_matriculas.web.dto.CursoDto;
import com.desafio02.alunos_matriculas.web.dto.ListaAlunosDeCursoDto;
import com.desafio02.alunos_matriculas.web.dto.MatriculaDto;
import com.desafio02.alunos_matriculas.web.dto.mapper.AlunoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatriculaService {

    private final AlunoRepository alunoRepository;
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
    public void salvar (MatriculaDto matriculaDto) {
        Matricula matricula = new Matricula();
        try {
            CursoDto curso = cursoClient.getCursoById(matriculaDto.getIdCurso());
            Aluno aluno = alunoController.getById(matriculaDto.getIdAluno()).getBody();

            if (cursoClient.getTotalAlunos(curso.getId()) >= 10) {
                throw new RuntimeException("Não pode haver mais de dez alunos matriculados.");
            }
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
        matriculaRepository.save(matricula);
        cursoClient.matricular(matricula.getIdCurso());
    }

    @Transactional
    public Matricula inativarMatricula(Long id) {
        Matricula matricula = buscarPorId(id);
        matricula.setAtivo(false);
        cursoClient.desamatricular(buscarPorId(id).getIdCurso());
        return matricula;
    }

    @Transactional
    public void apagarMatricula(Long id) {
        matriculaRepository.delete(buscarPorId(id));
        cursoClient.desamatricular(buscarPorId(id).getIdCurso());
    }

    @Transactional
    public ListaAlunosDeCursoDto buscarAlunosPorCurso(Long idCurso) {
        ListaAlunosDeCursoDto listaAlunosDto = new ListaAlunosDeCursoDto();
        List<Long> idAlunos = new ArrayList<>();

        CursoDto curso = cursoClient.getCursoById(idCurso);

        for (Matricula matricula : matriculaRepository.findAll()) {
            if (matricula.getIdCurso().equals(idCurso)) {
                idAlunos.add(matricula.getIdAluno());
            }
        }
        for (Long id : idAlunos) {
            listaAlunosDto.getAlunos().add( AlunoMapper.toDto(alunoRepository.findById(id).orElseThrow()));
        }

        listaAlunosDto.setTotalAlunos(listaAlunosDto.getAlunos().size());
        listaAlunosDto.setProfessor(curso.getProfessor());
        listaAlunosDto.setCurso(curso.getNome());
        return listaAlunosDto;
    }
}