package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;
import org.codedontblow.dao.BoletimDAO;
import org.codedontblow.dao.CandidatoDAO;
import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, SQLException {
        System.out.println("Hello CodeDontBlow!");
        InputDocuments i1 = new InputDocuments();
        OllamaApi api = new OllamaApi();
        CandidatoDAO candidatos = new CandidatoDAO();
        BoletimDAO boletins = new BoletimDAO();

        String filePath = i1.selectFile();
        String output = api.processFile(filePath);

        candidatos.ler();
        boletins.ler();
    }
}
