package com.desafio02.alunos_matriculas.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String messagem) {
        super(messagem);
    }
}
