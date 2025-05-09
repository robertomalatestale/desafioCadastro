package org.aplicacao.controller;

import org.aplicacao.model.MenuInicial;
import org.aplicacao.service.PetCrudService;
import org.aplicacao.view.MenuInicialView;

import java.io.IOException;

public class MenuInicialController {
    private MenuInicial menuInicial = new MenuInicial();
    private MenuInicialView menuInicialView = new MenuInicialView();
    private PetCrudService petCrudService = new PetCrudService();
    private Integer answer = -1;


    public void initializeQuestionary(){
        while(answer>menuInicial.getPerguntas().size() || answer<0){
            menuInicialView.showOptions();
            answer = menuInicialView.getUserAnswer();
            while(answer>menuInicial.getPerguntas().size() || answer<=0) {
                System.out.println("Opção inválida, digite novamente");
                answer = menuInicialView.getUserAnswer();
            }
            switch(answer){
                case 6:
                    petCrudService.finish();
            }
        }
    }

    public void showQuestions() {
        try {
            menuInicialView.showQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
