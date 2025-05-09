package org.aplicacao.service;

import org.aplicacao.model.Pet;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PetCrudServiceTest {

    Pet pet = new Pet();
    PetCrudService petCrudService = new PetCrudService();

    @Test
    void shouldNameGiveCorrectPetId(){
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        String date = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hours = now.format(DateTimeFormatter.ofPattern("HHmm"));
        pet.setNomeCompleto("Mario Jorge");
        assertEquals(date + "T" + hours + "-MARIOJORGE",petCrudService.getPetId(pet));

    }

}