package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.codedontblow.dao.InsertSQL;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;
import org.codedontblow.services.TesseractOCR;

import java.io.IOException;
import java.sql.SQLException;

import static org.codedontblow.services.TesseractOCR.processarImagem;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, SQLException {
        System.out.println("Hello CodeDontBlow!");
        InputDocuments i1 = new InputDocuments();
        OllamaApi api = new OllamaApi();

        String filePath = i1.selectFile();
        String output = api.processFile(filePath);
    }
}
