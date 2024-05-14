package com.desafio02.microServiceAlunos.services;

import com.desafio02.microServiceAlunos.client.CursoClient;
import com.desafio02.microServiceAlunos.entities.Aluno;
import com.desafio02.microServiceAlunos.entities.Matricula;
import com.desafio02.microServiceAlunos.exceptions.NotAllowedException;
import com.desafio02.microServiceAlunos.exceptions.UnableException;
import com.desafio02.microServiceAlunos.exceptions.UnprocessableEntityException;
import com.desafio02.microServiceAlunos.repositories.AlunoRepository;
import com.desafio02.microServiceAlunos.repositories.MatriculaRepository;
import com.desafio02.microServiceAlunos.web.controller.AlunoController;
import com.desafio02.microServiceAlunos.web.dto.CursoDto;
import com.desafio02.microServiceAlunos.web.dto.ListaAlunosDeCursoDto;
import com.desafio02.microServiceAlunos.web.dto.MatriculaDto;
import com.desafio02.microServiceAlunos.web.dto.mapper.AlunoMapper;
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
                () -> new EntityNotFoundException(String.format("Matrícula id=%s não encontrada", id))
        );
    }

    @Transactional
    public Matricula salvar (MatriculaDto matriculaDto) {
        Matricula matricula = new Matricula();
        try {
            CursoDto curso = cursoClient.getCursoById(matriculaDto.getIdCurso());
            Aluno aluno = alunoController.getById(matriculaDto.getIdAluno()).getBody();

            if (cursoClient.getTotalAlunos(curso.getId()) >= 10) {
                throw new NotAllowedException("Não pode haver mais de dez alunos matrículados.");
            }
            if (!curso.isAtivo()) {
                throw new UnableException("O curso não está ativo e o aluno não pode ser matrículado.");
            }
            if (!aluno.isAtivo()) {
                throw new UnableException("O aluno não está ativo e não pode se matrícular.");
            }
            matricula.setIdCurso(curso.getId());
            matricula.setIdAluno(aluno.getId());
            matricula.setAtivo(true);
            matriculaRepository.save(matricula);
            cursoClient.matricular(matricula.getIdCurso());
            return matricula;
        }
        catch (RuntimeException ex) {
            throw new UnprocessableEntityException("Matrícula inválida, por dados iválidos.");
        }
    }

    @Transactional
    public Matricula inativarMatricula(Long id) {
        Matricula matricula = buscarPorId(id);
        if (matricula.isAtivo()) {
            matricula.setAtivo(false);
            cursoClient.desamatricular(buscarPorId(id).getIdCurso());
        }
        else throw new UnableException("A matrícula já está desabilitada");
        return matricula;
    }

    @Transactional
    public ListaAlunosDeCursoDto buscarAlunosPorCurso(Long id) {
        ListaAlunosDeCursoDto listaAlunosDto = new ListaAlunosDeCursoDto();
        List<Long> idAlunos = new ArrayList<>();

        CursoDto curso = cursoClient.getCursoById(id);

        for (Matricula matricula : matriculaRepository.findAll()) {
            if (matricula.getIdCurso().equals(id)) {
                idAlunos.add(matricula.getIdAluno());
            }
        }
        for (Long i : idAlunos) {
            listaAlunosDto.getAlunos().add( AlunoMapper.toDto(alunoRepository.findById(i).orElseThrow()));
        }

        listaAlunosDto.setTotalAlunos(listaAlunosDto.getAlunos().size());
        listaAlunosDto.setProfessor(curso.getProfessor());
        listaAlunosDto.setCurso(curso.getNome());
        return listaAlunosDto;
    }
}