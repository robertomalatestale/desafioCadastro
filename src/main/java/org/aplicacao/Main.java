package org.aplicacao;

import org.aplicacao.controller.MenuInicialController;
import org.aplicacao.model.Endereco;
import org.aplicacao.model.Pet;
import org.aplicacao.service.PetCrudService;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        MenuInicialController menuInicialController = new MenuInicialController();
        menuInicialController.initializeQuestionary();
    }
}