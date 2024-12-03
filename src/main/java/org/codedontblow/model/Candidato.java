package org.codedontblow.model;

public class Candidato {

    //Atributos
    private int uniqueID;
    private String nome;
    private String numeroTelefone;
    private String email;
    private String endereco;
    private String competencias;
    private String idiomas;

    //Constructor
    public Candidato() {
    }

    public Candidato(String nome, String numeroTelefone, String email, String endereco, String competencias, String idiomas) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.endereco = endereco;
        this.competencias = competencias;
        this.idiomas = idiomas;
    }

    //Métodos getter and setter

    //UniqueID
    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    //Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Número de numeroTelefone
    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Endereço
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //Competências
    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }


    //Idiomas
    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

}
