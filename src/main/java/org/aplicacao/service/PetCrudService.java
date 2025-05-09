package org.aplicacao.service;

import org.aplicacao.model.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PetCrudService {

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

    public void finish(){
        System.out.println("\nAplicação finalizada com sucesso!");
    }



}
