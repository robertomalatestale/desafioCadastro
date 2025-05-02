package org.aplicacao.controller;

public class InvalidPetWeightException extends RuntimeException {
    public InvalidPetWeightException(String message) {
        super("Peso inválido: " + message);
    }
}
