package org.codedontblow.model;

public class TratamentoOutput {
    public static void main(String[] args) {
        //TESTE!!!
        String str = "12,jose/13,ana";
        String matricula = new String();
        String nome = new String();

        String[] linhas = str.split("/");

        for (String linha : linhas) {
            String[] atributos = linha.split(",");

            matricula = atributos[0];
            nome = atributos[1];

            System.out.printf("INSERT INTO boletim (matricula, nome) VALUES ('%s', '%s'); %n",matricula,nome);
        }
    }
}

