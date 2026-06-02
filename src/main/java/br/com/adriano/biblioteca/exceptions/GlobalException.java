package br.com.adriano.biblioteca.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException e,
                                                                 HttpServletRequest request) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<StandardError> ListEmptyException(ListEmptyException e,
                                                                    HttpServletRequest request) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> IllegalArgumentException(IllegalArgumentException e,
                                                                    HttpServletRequest request) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException e,
                                                                    HttpServletRequest request) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                    HttpServletRequest request) {
        ValidationError error = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Erro na validação dos campos", request.getRequestURI());

        for (FieldError obj : e.getBindingResult().getFieldErrors()) {
            error.addError(obj.getDefaultMessage(), obj.getField());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}