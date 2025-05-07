package org.aplicacao.model;

import org.aplicacao.controller.InvalidNameException;

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
}
