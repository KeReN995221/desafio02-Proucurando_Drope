package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.client.CursoClient;
import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.exceptions.CpfUniqueViolationException;
import com.desafio02.alunos_matriculas.exceptions.EntityNotFoundException;
import com.desafio02.alunos_matriculas.exceptions.UnableAlunoException;
import com.desafio02.alunos_matriculas.repositories.AlunoRepository;
import com.desafio02.alunos_matriculas.repositories.MatriculaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;
    @Autowired
    private final CursoClient cursoClient;

    @Transactional
    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException("O cpf do aluno deve ser único");
        }
    }

    @Transactional
    public Aluno inabilitarAluno(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aluno não encontrado")
        );
        if (aluno.isAtivo()) {
            aluno.setAtivo(false);
        }
        else throw new UnableAlunoException("O aluno já esta desabilitado");

        List<Matricula> matriculaLista = buscarMatriculasPorAluno(id);
        for(Matricula matricula : matriculaLista){
            matricula.setAtivo(false);
            cursoClient.desamatricular(matricula.getIdCurso());
        }
        return aluno;
    }

    @Transactional
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Aluno id=%s não encontrado", id))
        );
    }

    @Transactional
    public List<Aluno> buscarTodos() {
        return alunoRepository.findAll();
    }

    @Transactional
    public List<Matricula> buscarMatriculasPorAluno(Long id) {
        List<Matricula> listaMatriculas = new ArrayList<>();
        for (Matricula matricula : matriculaRepository.findAll()) {
            if (matricula.getIdAluno().equals(id)) {
                listaMatriculas.add(matricula);
            }
        }
        return listaMatriculas;
    }

    @Transactional
    public Aluno mudarAluno(Long id, Aluno alunoEditado) {
        Aluno aluno = buscarPorId(id);
        aluno.setAtivo(alunoEditado.isAtivo());
        aluno.setCpf(alunoEditado.getCpf());
        aluno.setSexo(alunoEditado.getSexo());
        aluno.setDataNascimento(alunoEditado.getDataNascimento());
        aluno.setNome(alunoEditado.getNome());
        return salvar(aluno);
    }

    @Transactional
    public void apagarAluno(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}
