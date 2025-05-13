package org.aplicacao.view;

import org.aplicacao.controller.InvalidInputException;
import org.aplicacao.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public String searchPetType(){
        System.out.println("Para começarmos a busca, digite o tipo do animal (cachorro ou gato)");
        return String.valueOf(TipoPet.identifyPetType(scanner.nextLine()));
    }

    public String showOptionName(int option){
        return switch (option){
            case 1 -> "Nome ou Sobrenome";
            case 2 -> "Sexo";
            case 3 -> "Idade";
            case 4 -> "Peso";
            case 5 -> "Raça";
            case 6 -> "Endereço";
            default -> throw new InvalidInputException("Número inválido");
        };
    }

    private int askOption(String message, List<Integer> optionsChoose){
        System.out.println(message);
        int opcao = scanner.nextInt();
        scanner.nextLine();
        if(optionsChoose.contains(opcao)){
            throw new InvalidInputException("Escolha uma opção diferente da primeira que você já selecionou");
        }
        if(opcao < 1 || opcao > 6){
            throw new InvalidInputException("Escolha um número correspondente");
        }
        return opcao;
    }

    public List<Integer> searchPetOptions(){
        List<Integer> optionsChoose = new ArrayList<>();
        int firstChoice = askOption("Escolha 1 atributo para buscar o pet, informe o número correspondente a opção\n1 - Nome ou Sobrenome\n2 - Sexo\n3 - Idade\n4 - Peso\n5 - Raça\n6 - Endereço",optionsChoose);
        optionsChoose.add(firstChoice);
        System.out.println("Você selecionou: " + showOptionName(firstChoice));
        System.out.println("Deseja procurar por mais algum atributo? Digite y se sim ou n para não");
        String oneMoreAttributeChoice = scanner.nextLine();
        if(!oneMoreAttributeChoice.equalsIgnoreCase("y") && !oneMoreAttributeChoice.equalsIgnoreCase("n")){
            throw new InvalidInputException("Digite y ou n");
        }
        if(oneMoreAttributeChoice.equalsIgnoreCase("y")){
            int secondChoice = askOption("Escolha mais 1 atributo para buscar o pet, informe o número correspondente a opção (EXCETO O ATRIBUTO QUE VOCÊ JÁ SELECIONOU ANTERIORMENTE",optionsChoose);
            optionsChoose.add(secondChoice);
            System.out.println("Você selecionou: " + showOptionName(secondChoice));
            return optionsChoose;
        }
        return optionsChoose;
    }


}
