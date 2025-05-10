package org.aplicacao.model;

import org.aplicacao.controller.InvalidInputException;

public enum TipoPet {
    CACHORRO(new String[]{"cão","cao","cachorro","dog"}),
    GATO(new String[]{"gato","cat"});

    private final String[] aliases;

    TipoPet(String[] aliases){
        this.aliases = aliases;
    }

    public static TipoPet identifyPetType(String userInput){
        for(TipoPet tipoPet : TipoPet.values()){
            for(String alias : tipoPet.aliases){
                if(alias.equalsIgnoreCase(userInput.trim())){
                    return tipoPet;
                }
            }
        }
        throw new InvalidInputException("Escolha entre cachorro e gato");
    }
}
