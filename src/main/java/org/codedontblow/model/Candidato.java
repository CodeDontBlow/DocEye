package org.codedontblow.model;

public class Candidato {

    //Atributos
    private int uniqueIDCandidato;
    private String nome;

    //Constructor
    public Candidato() {
    }

    public Candidato(String nome, String tipoDoc) {
        this.nome = nome;
    }


    //Métodos getter and setter

    public int getUniqueIDCandidato() {
        return uniqueIDCandidato;
    }

    public void setUniqueIDCandidato(int uniqueIDCandidato) {
        this.uniqueIDCandidato = uniqueIDCandidato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
