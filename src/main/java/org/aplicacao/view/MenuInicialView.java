package org.aplicacao.view;

import org.aplicacao.controller.InvalidInputException;
import org.aplicacao.model.MenuInicial;

import java.util.Scanner;

public class MenuInicialView {
    MenuInicial menuInicial = new MenuInicial();
    Scanner scanner = new Scanner(System.in);

    public void showQuestions() {
        for (int i = 0; i < menuInicial.getPerguntas().size(); i++) {
            System.out.println(menuInicial.getPerguntas().get(i));
        }
    }

    public void getUserAnswer() {
        Integer answer = scanner.nextInt();
        if(answer.getClass() != Integer.class){
            throw new InvalidInputException("Insira um número inteiro correspondente a opção que deseja executar");
        }
    }
}
