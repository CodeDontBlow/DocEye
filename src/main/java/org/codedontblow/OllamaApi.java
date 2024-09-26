package org.codedontblow.util;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OllamaApi {
    public String processFile(String filePath) throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://localhost:11434/"; //URL do Ollama
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(5000); //Tempo de espera para obter resposta

        OllamaResult result = ollamaAPI.generateWithImageFiles("moondream", //Nome do modelo de IA
                "Can you access the image??", //O comando
                List.of(
                        new File(filePath)), //Caminho da imagem
                new OptionsBuilder().build()
        );
        return result.getResponse();
    }
}
