package com.desafio02.microServiceAlunos.exceptions;

public class InvailidException extends RuntimeException {
    public InvailidException(String mensagem) {
            super(mensagem);
    }
}
