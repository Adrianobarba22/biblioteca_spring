package br.com.adriano.biblioteca.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    List<FieldErrors> errors = new ArrayList<>();

    public ValidationError(java.time.LocalDateTime timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public java.util.List<FieldErrors> getErrors() {
        return errors;
    }

        public void addError(String defaultMessage, String field) {
        this.errors.add(new FieldErrors(defaultMessage, field));
        }
}