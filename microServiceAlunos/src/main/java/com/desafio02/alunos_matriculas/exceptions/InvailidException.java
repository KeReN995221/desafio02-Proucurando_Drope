package com.desafio02.alunos_matriculas.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class InvailidException extends RuntimeException {
    public InvailidException(String mensagem) {
            super(mensagem);
    }
}
