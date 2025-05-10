package org.aplicacao.model;

import org.aplicacao.controller.InvalidInputException;

public enum Sexo {
    MASCULINO(new String[]{"m","masculino","macho","male"}),
    FEMININO(new String[]{"f","feminino","femea","fêmea","female"});

    private final String[] aliases;

    Sexo(String[] aliases){
        this.aliases = aliases;
    }

    public static Sexo identifyGender(String userInput){
        for (Sexo sexo : Sexo.values()){
            for (String alias : sexo.aliases){
                if(alias.equalsIgnoreCase(userInput.trim())){
                    return sexo;
                }
            }
        }
        throw new InvalidInputException("Escolha entre masculino e feminino");
    }

}
