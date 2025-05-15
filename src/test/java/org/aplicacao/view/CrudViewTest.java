package org.aplicacao.view;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CrudViewTest {

    CrudView crudView = new CrudView();
    File petFile = new File("petsCadastrados/20250511T1916-MARIAJOAQUINA.txt");
    @Test
    void shouldPrintSearchedPetsFormatted() {
        crudView.printSearchedPetsFormatted(petFile,1);
    }
}