package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.codedontblow.dao.BoletimDAO;
import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.dao.CurriculoDAO;
import org.codedontblow.model.Boletim;
import org.codedontblow.model.Candidato;
import org.codedontblow.model.Curriculo;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
//        System.out.println("Hello CodeDontBlow!");
//        InputDocuments i1 = new InputDocuments();
//        OllamaApi api = new OllamaApi();
//
//        String filePath = i1.selectFile();
//
//        api.processFile(filePath);

//        teste create CandidatoDAO
        CandidatoDAO candidatoDAO = new CandidatoDAO();

        Candidato ygor = new Candidato( "Ygor", "Curriculo");

        candidatoDAO.cadastrar(ygor);

//        teste create CurriculoDAO
        CurriculoDAO curriculoDAO = new CurriculoDAO();

        Curriculo curriculoYgor = new Curriculo("12996515989", "ygorrp25@gmail.com", "link.ygor", "git.ygor", "rua das andorinhas", "excel-avançado", "ingles", ygor.getUniqueIDCandidato());

        curriculoDAO.cadastrar(curriculoYgor);

//        teste create BoletimDAO
        BoletimDAO boletimDAO = new BoletimDAO();

        Boletim boletimYgor = new Boletim("12345", "palmyra", 9.5, 10.0, ygor.getUniqueIDCandidato());

        boletimDAO.cadastrar(boletimYgor);
    }
}
