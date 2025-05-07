package org.aplicacao.model;

import org.aplicacao.controller.InvalidNameException;
import org.aplicacao.controller.InvalidPetAgeException;
import org.aplicacao.controller.InvalidPetWeightException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    Pet dog = new Pet();


    @Test
    void shouldThrownExceptionIfNameIsEmpty(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> dog.setNomeCompleto("  "));
    }

    @Test
    void shouldThrownExceptionIfNameIsNull(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> dog.setNomeCompleto(null));
    }

    @Test
    void shouldThrownExceptionIfPetNameContainsSpecialCharactersOrNumbers(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> dog.setNomeCompleto("Marcelo D2"));
    }

    @Test
    void shouldThrownExceptionIfPetDoesntHaveLastName(){
        InvalidNameException exception = assertThrows(InvalidNameException.class,()-> dog.setNomeCompleto("Marcelo  "));
    }

    @Test
    void shouldThrownExceptionIfWeightAboveSixty(){
        InvalidPetWeightException exception = assertThrows(InvalidPetWeightException.class,() -> dog.setPeso(60.01));
    }

    @Test
    void shouldThrownExceptionIfWeightBelowOneHalf(){
        InvalidPetWeightException exception = assertThrows(InvalidPetWeightException.class,() -> dog.setPeso(0.499));
    }

    @Test
    void shouldThrownExceptionIfAgeAboveTwenty(){
        InvalidPetAgeException exception = assertThrows(InvalidPetAgeException.class,() -> dog.setIdade(20.01));
    }

    @Test
    void shouldThrownExceptionIfAgeBelowOrEqualZero(){
        InvalidPetAgeException exception = assertThrows(InvalidPetAgeException.class,() -> dog.setIdade(-0.01));
    }

    @Test
    void shouldRoundToOneDecimalIfAgeBetweenZeroAndOne(){
        dog.setIdade(0.31);
        assertEquals(0.3,dog.getIdade());
    }

    @Test
    void shouldRoundAgeToNoDecimalsIfAboveOneYearsOld(){
        dog.setIdade(1.3);
        assertEquals(1,dog.getIdade());
    }
}