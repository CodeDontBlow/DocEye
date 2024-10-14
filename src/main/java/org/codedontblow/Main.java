package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.codedontblow.dao.InsertSQL;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, SQLException {
        System.out.println("Hello CodeDontBlow!");
        InputDocuments i1 = new InputDocuments();
        OllamaApi api = new OllamaApi();

        String filePath = i1.selectFile();

        api.processFile(filePath);

        //Inserção de dados do output dentro do banco de dados

        // Inserir Boletim
        InsertSQL.insereOutput("Aurora,12,Escola Prof. Juliana,9.0,8.0/Renata13,Escola Prof. Josias,7.6,5.5/Gabriel,14,Escola Prof. Maria José,10.0,6.5", "Boletim");
        // Inserir Curriculo
        InsertSQL.insereOutput("Carlos,99612345,carlos@gmail.com,@carlos123,carlos.com,R. Carlos,Programação,Portugues/Ana,99418949,ana@gmail.com,@ana123,ana.com,R. Ana,Marketing,Inglês" , "Curriculo");
    }
}
