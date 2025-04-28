package org.aplicacao.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReaderServiceTest {
    ReaderService readerService = new ReaderService();
    String formText = "1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)?\n3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n5 - Qual a idade aproximada do pet?\n6 - Qual o peso aproximado do pet?\n7 - Qual a raça do pet?";

    @Test
    void shouldReadTheCorrectFormTxtMessage() throws IOException {
        String testReader = readerService.readFormTxt().replace("\r\n", "\n");
        assertEquals(formText,testReader);
    }

}