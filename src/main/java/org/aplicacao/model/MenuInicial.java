package org.aplicacao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuInicial {
    private final String[] perguntasIniciais = {"1. Cadastrar um novo pet","2. Alterar os dados do pet cadastrado", "3. Deletar um pet cadastrado", "4. Listar os pets cadastrados", "5. Listar pets por algum critério (idade,nome ou raça)","6. Sair"};
    private List<String> perguntas = new ArrayList<String>();

    public MenuInicial() {
        this.perguntas.addAll(Arrays.asList(this.perguntasIniciais));
    }

    public List<String> getPerguntas() {
        return perguntas;
    }
}
