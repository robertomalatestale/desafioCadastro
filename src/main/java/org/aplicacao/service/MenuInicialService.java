package org.aplicacao.service;

import org.aplicacao.controller.InvalidInputException;
import org.aplicacao.model.MenuInicial;

public class MenuInicialService {
    MenuInicial menuInicial = new MenuInicial();

    public void menuInicialUserAnswerLogic(Integer answer){
        if(answer<=0){
            throw new InvalidInputException("Insira um número correspondente a opção que deseja executar");
        }
    }
}
