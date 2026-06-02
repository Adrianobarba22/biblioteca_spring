package br.com.adriano.biblioteca.exceptions;

public class ListEmptyException extends RuntimeException {

    public ListEmptyException(String message) {
        super(message);
    }
}
