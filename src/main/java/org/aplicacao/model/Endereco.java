package org.aplicacao.model;

import org.aplicacao.controller.InvalidNameException;

import java.util.Scanner;

public class Endereco {
    private String logradouro;
    private Integer numero;
    private String cidade;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if(logradouro == null || logradouro.trim().isEmpty()){
            throw new InvalidNameException("Campo logradouro não pode ficar vazio");
        }
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if(cidade == null || cidade.trim().isEmpty()){
            throw new InvalidNameException("Campo cidade não pode ficar vazio");
        }
        this.cidade = cidade;
    }

    public void setEnderecoInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe seu logradouro: ");
        setLogradouro(scanner.nextLine());
        System.out.println("Informe o número da sua residência: ");
        setNumero(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Informe sua cidade: ");
        setCidade(scanner.nextLine());
    }
}
