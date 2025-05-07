package org.aplicacao.controller;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String message) {
        super("Nome inválido: " + message);
    }
}
