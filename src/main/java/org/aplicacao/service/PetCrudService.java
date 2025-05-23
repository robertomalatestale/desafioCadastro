package org.aplicacao.service;

import org.aplicacao.model.Endereco;
import org.aplicacao.model.Pet;
import org.aplicacao.view.CrudView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetCrudService {

    private CrudView crudView = new CrudView();

    public String getPetId(Pet pet){
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        String date = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hours = now.format(DateTimeFormatter.ofPattern("HHmm"));
        String petName = pet.getNomeCompleto().toUpperCase().replace(" ","");
        return date + "T" + hours + "-" + petName;
    }

    public void createPet(Pet pet){
        Path path = Paths.get("petsCadastrados/" + getPetId(pet) + ".txt");
        String data = "1 - " + pet.getNomeCompleto() + "\n2 - " + pet.getTipoPet() + "\n3 - " + pet.getSexo() + "\n4 - " + pet.getEndereco().getLogradouro() + ", " + pet.getEndereco().getNumero() + ", " + pet.getEndereco().getCidade() + "\n5 - " + pet.getIdade() + " anos\n6 - " + pet.getPeso() + "kg\n7 - " + pet.getRaca();

        try{
            if(Files.notExists(path.getParent())){
                Files.createDirectories(path.getParent());
            }
            Files.writeString(path,data);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updatePet(){
        Pet pet = new Pet();
        Endereco endereco = new Endereco();
        List<File> petsSearchedToUpdate = searchPet();
        File petToUpdate = crudView.selectPetInSearchFieldFromUser(petsSearchedToUpdate,"atualizar");
        if(petToUpdate == null){
            return;
        }
        int attributeToUpdate = crudView.selectPetAttributeToUpdate();
        if(attributeToUpdate == 0){
            System.out.println("OPERAÇÃO CANCELADA, SAINDO DA APLICAÇÃO");
            return;
        }
        try{
            List<String> lines = Files.readAllLines(petToUpdate.toPath());
            switch (attributeToUpdate){
                case 1:
                    pet.setNomeCompleto(crudView.getUserInput("Digite o novo nome e sobrenome do pet: "));
                    lines.set(0,"1 - " + pet.getNomeCompleto());
                case 2:
                    endereco.setEnderecoInput();
                    lines.set(3,"4 - " + endereco.getLogradouro() + ", " + endereco.getNumero() + ", " + endereco.getCidade());
                    break;
                case 3:
                    pet.setIdade(crudView.getDoubleInput("Digite a nova idade do pet: "));
                    lines.set(4,"5 - " + pet.getIdade() + " anos");
                    break;
                case 4:
                    pet.setPeso(crudView.getDoubleInput("Digite o novo peso do pet: "));
                    lines.set(5,"6 - " + pet.getPeso() + "kg");
                    break;
                case 5:
                    pet.setRaca(crudView.getUserInput("Digite a nova raça do pet: "));
                    lines.set(6,"7 - " + pet.getRaca());
                default:
                    break;
            }
            Files.write(petToUpdate.toPath(),lines);
            System.out.println("PET ATUALIZADO COM SUCESSO!");
        } catch (IOException e){
            System.out.println("Falha ao atualizar o pet: " + e.getMessage());
        }
    }

    public void deletePet(){
        List<File> petsSearchedToDelete = searchPet();
        File petToDelete = crudView.selectPetInSearchFieldFromUser(petsSearchedToDelete,"deletar");
        if(petToDelete == null ){
            return;
        }
        String userConfirmationToDelete = crudView.confirmsPetDelete();
        while(!userConfirmationToDelete.equalsIgnoreCase("Sim") && !userConfirmationToDelete.equalsIgnoreCase("Não")){
            userConfirmationToDelete = crudView.confirmsPetDelete();
        }
        if(userConfirmationToDelete.equalsIgnoreCase("Sim")){
            petToDelete.delete();
            System.out.println("PET DELETADO COM SUCESSO");
        } else {
            System.out.println("OPERAÇÃO CANCELADA, APLICAÇÃO FINALIZADA");
        }

    }

    public void printAllPets(){
        File directory = new File("petsCadastrados");
        File[] files = directory.listFiles((dir,name) -> name.endsWith(".txt"));
        int i = 1;
        if(files == null || files.length == 0){
            System.out.println("Nenhum arquivo encontrado");
            return;
        }
        for (File file:files){
            crudView.printSearchedPetsFormatted(file,i++);
        }
    }

    public List<File> searchPet(){
        Object petAttributeInput1 = null;
        Object petAttributeInput2 = null;
        String petTypeInput = crudView.searchPetType();
        List<Integer> userSearchChoices = crudView.searchPetOptions();
        if(userSearchChoices.size() == 2){
            petAttributeInput1 = crudView.getSearchInput(userSearchChoices.get(0));
            petAttributeInput2 = crudView.getSearchInput(userSearchChoices.get(1));
        } else if(userSearchChoices.size() == 1) {
            petAttributeInput1 = crudView.getSearchInput(userSearchChoices.get(0));
        }

        return searchPetInFiles(petTypeInput, petAttributeInput1, petAttributeInput2);
    }

    public List<File> searchPetInFiles(String petType, Object attribute1, Object attribute2){
            File directory = new File("petsCadastrados");
            File[] files = directory.listFiles((dir,name) -> name.endsWith(".txt"));
            List<File> matchingPetFiles = new ArrayList<>();
            int counter = 1;
            if(files == null || files.length == 0){
                System.out.println("Nenhum arquivo encontrado");
                return matchingPetFiles;
            }

            String normalizedPetType = crudView.toLowerCaseAndRemoveAccent(petType);

            boolean isAttribute1Numeric = attribute1 instanceof Number;
            boolean isAttribute2Numeric = attribute2 instanceof Number;
            String normalizedAttribute1 = null;
            if(!isAttribute1Numeric){
                normalizedAttribute1 = crudView.toLowerCaseAndRemoveAccent(attribute1.toString());
            }
            String normalizedAttribute2 = null;
            if(attribute2 != null && !isAttribute2Numeric) {
                normalizedAttribute2 = crudView.toLowerCaseAndRemoveAccent(attribute2.toString());
            }
            for (File file: files){
                try{
                    List<String> lines = Files.readAllLines(file.toPath());
                    String fileType = lines.get(1).replaceFirst("^\\d+\\s*-\\s*", "").trim();
                    if(!crudView.toLowerCaseAndRemoveAccent(fileType).equals(normalizedPetType)) {
                        continue;
                    }
                    StringBuilder fullText = new StringBuilder();
                    for(String line : lines){
                        fullText.append(line.replaceFirst("^\\d+\\s*-\\s*", "").trim()).append(" ");
                    }
                    String normalizedFullText = crudView.toLowerCaseAndRemoveAccent(fullText.toString());
                    String fullTextOriginal = fullText.toString();

                    boolean containsAttribute1;
                    if (isAttribute1Numeric){
                        double numericValue = ((Number) attribute1).doubleValue();
                        containsAttribute1 = crudView.matchesNumericAttribute(fullTextOriginal, numericValue, "anos", "kg");
                    } else {
                        containsAttribute1 = normalizedFullText.contains(normalizedAttribute1);
                    }
                    boolean containsAttribute2 = false;
                    if (attribute2 == null) {
                        containsAttribute2 = true;
                    } else if (isAttribute2Numeric) {
                        double numericValue = ((Number) attribute2).doubleValue();
                        containsAttribute2 = crudView.matchesNumericAttribute(fullTextOriginal, numericValue, "anos", "kg");
                    } else if (normalizedAttribute2 != null && normalizedFullText.contains(normalizedAttribute2)) {
                        containsAttribute2 = true;
                    }
                    if(containsAttribute1 && containsAttribute2){
                        matchingPetFiles.add(file);
                        crudView.printSearchedPetsFormatted(file,counter++);
                    }
                } catch (IOException e){
                    System.out.println("Erro ao ler o arquivo: " + file.getName());
                }
            }
        if(matchingPetFiles.isEmpty()){
            System.out.println("NENHUM PET FOI ENCONTRADO COM ESSAS CARACTERÍSTICAS!");
        }
        return matchingPetFiles;
    }

    public void finish(){
        System.out.println("\nAplicação finalizada com sucesso!");
    }



}
