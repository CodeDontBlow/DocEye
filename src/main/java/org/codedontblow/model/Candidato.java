package org.codedontblow.model;

public class Candidato {

    //Atributos
    private int uniqueIDCandidato;
    private String nome;
    private String tipoDoc;


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
