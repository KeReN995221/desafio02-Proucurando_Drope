package com.desafio02.microServiceAlunos.exceptions;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String mensagem) {
        super(mensagem);
    }
}
