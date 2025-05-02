package org.aplicacao.view;

import org.aplicacao.model.MenuInicial;
import org.aplicacao.service.MenuInicialService;
import org.aplicacao.service.ReaderService;

import java.io.IOException;
import java.util.Scanner;

public class MenuInicialView {
    private MenuInicial menuInicial = new MenuInicial();
    private MenuInicialService menuInicialService = new MenuInicialService();
    private ReaderService readerService = new ReaderService();
    private Scanner scanner = new Scanner(System.in);

    public void showOptions() {
        System.out.println();
        for (int i = 0; i < menuInicial.getPerguntas().size(); i++) {
            System.out.println(menuInicial.getPerguntas().get(i));
        }
        System.out.println();
    }

    public Integer getUserAnswer() {
        System.out.println("Por favor digite a opção que quer executar");
        Integer answer = scanner.nextInt();
        menuInicialService.menuInicialUserAnswerExceptionLogic(answer);
        return answer;
    }

    public void showQuestions() throws IOException {
        System.out.println(readerService.readFormTxt());
    }

}
