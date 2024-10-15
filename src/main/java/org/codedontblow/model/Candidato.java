package org.codedontblow.model;

public class Candidato {

    //Atributos
    private int uniqueIDCandidato;
    private String nome;
    private String tipoDoc;

    //Constructor
    public Candidato() {
    }

    public Candidato(String nome, String tipoDoc) {
        this.nome = nome;
        this.tipoDoc = tipoDoc;
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

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
}
