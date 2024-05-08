package com.desafio02.alunos_matriculas.services;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

        @Transactional
        public Aluno salvar(Aluno aluno) {
            return alunoRepository.save(aluno);
        }


}
