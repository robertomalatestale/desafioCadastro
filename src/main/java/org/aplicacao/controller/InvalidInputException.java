package org.aplicacao.controller;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super("Resposta não é válida: " + message);
    }
}
