package org.aplicacao.view;

import org.aplicacao.service.ReaderService;

import java.io.IOException;

public class FormView {
    private final ReaderService readerService = new ReaderService();

    public void showForm(){
        try{
        System.out.println(readerService.readFormTxt());
        } catch (IOException e) {
            System.err.println("Erro ao ler o formulário " + e.getMessage());
        }
    }
}
