package org.aplicacao.model;

import org.aplicacao.controller.InvalidNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {
    Endereco endereco = new Endereco();

    @Test
    void shouldThrownExceptionIfAddressIsEmpty(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> endereco.setLogradouro("  "));
    }

    @Test
    void shouldThrownExceptionIfCityIsEmpty(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> endereco.setCidade("  "));
    }
}