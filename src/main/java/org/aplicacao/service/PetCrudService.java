package org.aplicacao.service;

import org.aplicacao.model.Pet;

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

}
