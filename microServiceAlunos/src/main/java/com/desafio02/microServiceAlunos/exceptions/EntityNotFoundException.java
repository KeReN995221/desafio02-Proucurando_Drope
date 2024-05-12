package com.desafio02.microServiceAlunos.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String messagem) {
        super(messagem);
    }
}
