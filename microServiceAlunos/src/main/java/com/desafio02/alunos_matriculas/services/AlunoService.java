package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.exceptions.CpfUniqueViolationException;
import com.desafio02.alunos_matriculas.exceptions.EntityNotFoundException;
import com.desafio02.alunos_matriculas.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

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
        Aluno aluno = alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id de aluno não encontrado")
        );
        aluno.setAtivo(false);
        return aluno;
    }
}
