package org.aplicacao.service;

import org.aplicacao.controller.InvalidInputException;
import org.aplicacao.model.MenuInicial;

public class MenuInicialService {
    private MenuInicial menuInicial = new MenuInicial();

    public void menuInicialUserAnswerExceptionLogic(Integer answer){
        if(answer<=0){
            throw new InvalidInputException("Insira um número correspondente a opção que deseja executar");
        }
        if(answer.getClass() != Integer.class){
            throw new InvalidInputException("Insira um número inteiro correspondente a opção que deseja executar");
        }
    }
}
