package org.codedontblow.services;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.codedontblow.services.TesseractOCR.processarImagem;

public class OllamaApi {
    public static String processFile(String filePath) throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://localhost:11434/";
        String model = "moondream:latest";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(600);

        String text = processarImagem(filePath); // Métod do tesseract para OCR

        PromptBuilder boletimPrompt = new PromptBuilder()
                .addLine(text) //Adiciona a extração do OCR para usar como auxilio do prompt
                .addSeparator()
                .addLine("what is in image?")
                ;

        PromptBuilder curriculoPrompt = new PromptBuilder();

        OllamaResult result = ollamaAPI.generateWithImageFiles(model, //Nome do modelo de IA
                boletimPrompt.build(),//O comando do prompt
                List.of(
                        new File(filePath)), //Caminho da imagem
                new OptionsBuilder().build()
        );
        return result.getResponse() + result;
    }
}
