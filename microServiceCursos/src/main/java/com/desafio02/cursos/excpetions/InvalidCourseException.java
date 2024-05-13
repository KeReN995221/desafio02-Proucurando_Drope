package com.desafio02.cursos.excpetions;

public class InvalidCourseException extends RuntimeException {
    public InvalidCourseException(String mensagem) {
        super(mensagem);
    }
}
