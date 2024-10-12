package org.codedontblow.model;

public class Curriculo {

    //Atributos
    private int uniqueIDCurriculo;
    private String numeroTelefone;
    private String email;
    private String linkedin;
    private String portifolio;
    private String endereco;
    private String competencias;
    private String idiomas;

    //Constructor


    public Curriculo() {
    }

    public Curriculo(String numeroTelefone, String email, String linkedin, String portifolio, String endereco, String competencias, String idiomas) {
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.linkedin = linkedin;
        this.portifolio = portifolio;
        this.endereco = endereco;
        this.competencias = competencias;
        this.idiomas = idiomas;
    }


    //Métodos getter and setter

    public int getUniqueIDCurriculo() {
        return uniqueIDCurriculo;
    }

    public void setUniqueIDCurriculo(int uniqueID_Curriculo) {
        this.uniqueIDCurriculo = uniqueID_Curriculo;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPortifolio() {
        return portifolio;
    }

    public void setPortifolio(String portifolio) {
        this.portifolio = portifolio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }
}
