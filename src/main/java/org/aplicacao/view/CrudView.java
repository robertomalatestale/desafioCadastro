package org.aplicacao.view;

import org.aplicacao.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CrudView {

    private Scanner scanner = new Scanner(System.in);
    private MenuInicial menuInicial = new MenuInicial();


    public void assignPetInputs(Pet pet){
        System.out.println("");
        List<String> registerQuestions;
        Path path = Paths.get("formulario.txt");
        try{
        registerQuestions = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Endereco endereco = new Endereco();
        for(int i = 0 ; i<=menuInicial.getPerguntas().size();i++){
            System.out.println(registerQuestions.get(i));
            if(i==0) pet.setNomeCompleto(scanner.nextLine());
            if(i==1) pet.setTipoPet(TipoPet.identifyPetType(scanner.nextLine()));
            if(i==2) pet.setSexo(Sexo.identifyGender(scanner.nextLine()));
            if (i == 3) {
                endereco.setEnderecoInput();
                pet.setEndereco(endereco);
            }
            if(i==4) {
                pet.setIdade(scanner.nextDouble());
                scanner.nextLine();
            }
            if(i==5) {
                pet.setPeso(scanner.nextDouble());
                scanner.nextLine();
            }
            if(i==6) pet.setRaca(scanner.nextLine());
        }
    }
}
