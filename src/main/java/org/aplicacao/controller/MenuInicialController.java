package org.aplicacao.controller;

import org.aplicacao.model.MenuInicial;
import org.aplicacao.model.Pet;
import org.aplicacao.service.PetCrudService;
import org.aplicacao.view.CrudView;
import org.aplicacao.view.MenuInicialView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuInicialController {
    private MenuInicial menuInicial = new MenuInicial();
    private MenuInicialView menuInicialView = new MenuInicialView();
    private PetCrudService petCrudService = new PetCrudService();
    private CrudView crudView = new CrudView();
    private Map<Integer, Runnable> functions = new HashMap<>();

    public MenuInicialController() {
        initializeMapFunctions();
    }

    private void initializeMapFunctions(){
        functions.put(1, () -> createPet());
        functions.put(2, () -> updatePet());
        functions.put(3, () -> deletePet());
        functions.put(4, () -> showAllPets());
        functions.put(5, () -> searchPet());
        functions.put(6, () -> exitApplication());
    }


    public void initializeQuestionary(){
        int answer = -1;
        while(answer>menuInicial.getPerguntas().size() || answer<0){
            menuInicialView.showOptions();
            answer = menuInicialView.getUserAnswer();
            while(answer>menuInicial.getPerguntas().size() || answer<=0) {
                System.out.println("Opção inválida, digite novamente");
                answer = menuInicialView.getUserAnswer();
            }
            if(functions.get(answer) != null){
                functions.get(answer).run();
            } else {
                throw new InvalidInputException("Opção inválida");
            }
        }
    }

    private void createPet(){
        Pet newPet = new Pet();
        showQuestions();
        crudView.assignPetInputs(newPet);
        petCrudService.createPet(newPet);
    }

    private void updatePet(){
        System.out.println("Destarte, busque e selecione o pet que deseja atualizar");
        petCrudService.updatePet();
    }

    private void deletePet(){
        System.out.println("Destarte, busque e selecione o pet que deseja deletar");
        petCrudService.deletePet();
    }

    private void showAllPets(){
        petCrudService.printAllPets();
    }

    private void searchPet(){
        petCrudService.searchPet();
    }

    private void exitApplication(){
        petCrudService.finish();
    }

    public void showQuestions() {
        try {
            menuInicialView.showQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
