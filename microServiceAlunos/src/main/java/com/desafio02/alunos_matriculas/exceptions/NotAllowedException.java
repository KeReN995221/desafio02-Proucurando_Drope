package com.desafio02.alunos_matriculas.exceptions;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String mensagem) {
        super(mensagem);
    }
}
