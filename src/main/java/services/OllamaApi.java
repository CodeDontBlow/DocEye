package services;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OllamaApi {
    public String processFile(String filePath) throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://localhost:11434/";
        String model = "moondream:latest";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(600); //Tempo de espera para obter resposta

        PromptBuilder boletimPrompt = new PromptBuilder()
                .addLine("This document is a school report card.")
                .addSeparator()
                .addLine("In the first table located at the top of the image, there are the student's details.")
                .addSeparator()
                .addLine("Now, look at the table and search for \"Aluno:\".")
                .addSeparator()
                .addLine("Extract the student's name, it's to the right of \"Aluno:\", Extract the student's full name.")
                .addSeparator()
                .addLine("Follow all these steps and return only what was requested.")
                .addSeparator()
                .addLine("Remember, do it step by step.")
                ;

        PromptBuilder curriculoPrompt = new PromptBuilder();

        OllamaResult result = ollamaAPI.generateWithImageFiles(model, //Nome do modelo de IA
                boletimPrompt.build(),//O comando
                List.of(
                        new File(filePath)), //Caminho da imagem
                new OptionsBuilder().build()
        );
        return result.getResponse();
    }
}
