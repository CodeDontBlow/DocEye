package org.codedontblow.model;

public class Boletim {

    //Atributos
    private int uniqueIDBoletim;
    private String matricula;
    private String escola;
    private double notaPortugues;
    private double notaMatematica;
    private int candidatoID;


    //Constructor
    public Boletim() {
    }

    public Boletim(String matricula, String escola, double notaPortugues, double notaMatematica, int candidatoID) {
        this.matricula = matricula;
        this.escola = escola;
        this.notaPortugues = notaPortugues;
        this.notaMatematica = notaMatematica;
        this.candidatoID = candidatoID;
    }


    //Métodos getter and setter

    public int getUniqueIDBoletim() {
        return uniqueIDBoletim;
    }

    public void setUniqueIDBoletim(int uniqueIDBoletim) {
        this.uniqueIDBoletim = uniqueIDBoletim;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public double getNotaPortugues() {
        return notaPortugues;
    }

    public void setNotaPortugues(double notaPortugues) {
        this.notaPortugues = notaPortugues;
    }

    public double getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(double notaMatematica) {
        this.notaMatematica = notaMatematica;
    }
}
