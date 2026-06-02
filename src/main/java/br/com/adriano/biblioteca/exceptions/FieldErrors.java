package br.com.adriano.biblioteca.exceptions;

public class FieldErrors {

    private String defaultMessage;
    private String field;

    public FieldErrors(String defaultMessage, String field) {
        this.defaultMessage = defaultMessage;
        this.field = field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
