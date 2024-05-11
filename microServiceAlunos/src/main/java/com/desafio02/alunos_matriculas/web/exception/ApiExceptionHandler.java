package com.desafio02.alunos_matriculas.web.exception;

import com.desafio02.alunos_matriculas.exceptions.CpfUniqueViolationException;
import com.desafio02.alunos_matriculas.exceptions.EntityNotFoundException;
import com.desafio02.alunos_matriculas.exceptions.UnableAlunoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CpfUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex,
                                                                 HttpServletRequest request) {
        log.error("Api Error - "+ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(RuntimeException ex,
                                                                 HttpServletRequest request) {
        log.error("Api Error - "+ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // BAD_REQUEST -> NOT_FOUNF
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage())); // BAD_REQUEST -> NOT_FOUNF
    }
    @ExceptionHandler(UnableAlunoException.class)
    public ResponseEntity<ErrorMessage> UnableAlunoException(RuntimeException ex,
                                                          HttpServletRequest request) {
        log.error("Api Error - "+ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
