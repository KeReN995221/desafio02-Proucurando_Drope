package com.desafio02.cursos.excpetions;

public class NameUniqueViolationException extends RuntimeException {
    public NameUniqueViolationException(String message) {
        super(message);
    }
}
