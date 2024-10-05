package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import services.InputDocuments;
import services.OllamaApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
        System.out.println("Hello CodeDontBlow!");
        InputDocuments i1 = new InputDocuments();
        OllamaApi api = new OllamaApi();

        String filePath = i1.selectFile();

        api.processFile(filePath);
    }
}
