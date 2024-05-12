package com.desafio02.microServiceAlunos.exceptions;

public class UnableException extends RuntimeException{
    public UnableException(String messagem) {
        super(messagem);
    }
}
