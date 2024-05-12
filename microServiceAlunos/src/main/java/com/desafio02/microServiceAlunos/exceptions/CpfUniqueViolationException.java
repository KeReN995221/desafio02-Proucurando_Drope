package com.desafio02.microServiceAlunos.exceptions;

public class CpfUniqueViolationException extends RuntimeException{
    public CpfUniqueViolationException(String messagem) {
        super(messagem);
    }
}
