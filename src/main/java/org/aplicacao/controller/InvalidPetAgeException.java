package org.aplicacao.controller;

public class InvalidPetAgeException extends RuntimeException {
    public InvalidPetAgeException(String message) {
      super("Idade inválida: " + message);
    }
}
