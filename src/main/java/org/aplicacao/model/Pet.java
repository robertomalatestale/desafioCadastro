package org.aplicacao.model;

import org.aplicacao.controller.InvalidNameException;
import org.aplicacao.controller.InvalidPetAgeException;
import org.aplicacao.controller.InvalidPetWeightException;

public class Pet {
    private String nomeCompleto;
    private TipoPet tipoPet;
    private Endereco endereco;
    private double idade;
    private double peso;
    private String raca;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        if(nomeCompleto == null || nomeCompleto.trim().isEmpty()){
            throw new InvalidNameException("Deve cadastrar um nome para o pet");
        }
        for(int i = 0; i<nomeCompleto.length(); i++){
            if(!Character.isLetter(nomeCompleto.charAt(i)) && !Character.isWhitespace(nomeCompleto.charAt(i))){
                throw new InvalidNameException("Nome deve conter somente letras");
            }
        }
        int auxiliary = 0;
        for(int i =0; i<nomeCompleto.length(); i++){
            if(Character.isWhitespace(nomeCompleto.charAt(i))){
                auxiliary = i;
                break;
            }
        }
        if(auxiliary == 0){
            throw new InvalidNameException("Pet deve ter um sobrenome");
        }
        boolean temSobrenome = false;
        for(int i = auxiliary + 1; i<nomeCompleto.length(); i++){
            if(Character.isLetter(nomeCompleto.charAt(i))){
                temSobrenome = true;
                break;
            }
        }
        if(!temSobrenome){
            throw new InvalidNameException("Pet deve ter um sobrenome");
        }
        this.nomeCompleto = nomeCompleto;
    }

    public TipoPet getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet) {
        this.tipoPet = tipoPet;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        if(idade>20){
            throw new InvalidPetAgeException("Pet não pode ter mais que 20 anos");
        }
        if(idade<=0){
            throw new InvalidPetAgeException("Pet não pode ter idade nula ou negativa");
        }
        if(idade<1){
            this.idade = (double) Math.round(idade * 10)/10.0;
        } else {
        this.idade = (double) Math.round(idade);}
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if(peso>60 || peso<0.5){
            throw new InvalidPetWeightException("Pet deve ter entre 0,5 e 60 quilos");
        }
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


}
