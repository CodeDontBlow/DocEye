package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.codedontblow.dao.BoletimDAO;
import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.dao.CurriculoDAO;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {

        CandidatoDAO candidatos = new CandidatoDAO();
        BoletimDAO boletins = new BoletimDAO();

        candidatos.ler();
        boletins.ler();


    }
}
