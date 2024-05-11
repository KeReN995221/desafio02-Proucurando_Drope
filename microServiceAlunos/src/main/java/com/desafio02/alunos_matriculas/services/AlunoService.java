package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.client.CursoClient;
import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.exceptions.CpfUniqueViolationException;
import com.desafio02.alunos_matriculas.exceptions.EntityNotFoundException;
import com.desafio02.alunos_matriculas.exceptions.UnableException;
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
        if (aluno.isAtivo()) {
            aluno.setAtivo(false);
        }
        else throw new UnableException("O aluno já esta desabilitado");

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
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            throw new EntityNotFoundException("Nenhum aluno encontrado");
        }
        return alunos;
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
}
