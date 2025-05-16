package org.aplicacao.view;

import org.aplicacao.controller.InvalidInputException;
import org.aplicacao.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Object getSearchInput(int option){
        switch (option){
            case 1:
                System.out.println("Digite o nome e sobrenome do pet:");
                return scanner.nextLine();
            case 2:
                System.out.println("Digite o sexo do pet:");
                return Sexo.identifyGender(scanner.nextLine());
            case 3:
                System.out.println("Digite a idade do pet:");
                double idade = scanner.nextDouble();
                scanner.nextLine();
                return idade;
            case 4:
                System.out.println("Digite o peso do pet:");
                double peso = scanner.nextDouble();
                scanner.nextLine();
                return peso;
            case 5:
                System.out.println("Digite a raça do pet:");
                return scanner.nextLine();
            case 6:
                System.out.println("Digite o endereço");
                return scanner.nextLine();
            default:
                throw new InvalidInputException("Opção inválida");
        }
    }

    public void printSearchedPetsFormatted(File file, int petOrder){
        try{
            List <String> lines = Files.readAllLines(file.toPath());
            System.out.printf(petOrder + ". ");
            for(int i = 0; i < lines.size(); i++){
                String formattedLine = lines.get(i).replaceFirst("^\\d+\\s*-\\s*", "").trim(); //Remove the initial number with the hyphen
                System.out.printf(formattedLine);
                if(i<lines.size() - 1){
                    System.out.printf(" - ");
                }
            }
            System.out.println();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String toLowerCaseAndRemoveAccent(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase()
                .trim();
    }

    public boolean matchesNumericAttribute(String text, double value, String... units) {
        for (String unit : units) {
            Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)\\s*" + unit);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                double extracted = Double.parseDouble(matcher.group(1));
                if (Double.compare(extracted, value) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public File selectPetInSearchFieldFromUser(List<File> totalPets, String operation){
        if(totalPets.isEmpty()){
            System.out.println("Não é possível " + operation + " pois não existem pets com esses critérios");
            return null;
        }
        System.out.println("Digite o número do pet que deseja " + operation + " (ou se deseja cancelar a operação e finalizar, digite 0): ");
        int choice = -1;
        choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 0 || choice > totalPets.size()){
            System.out.println("Digite um número válido conforme as opções");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        if (choice == 0){
            System.out.println("OPERAÇÃO CANCELADA, SAINDO DA APLICAÇÃO");
            return null;
        }
        return totalPets.get(choice - 1);
    }

}
