package com.tesseract;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TesseractOCR {
    public static void main(String[] args) {
        String resultado = processarImagem("D:\\Tess4j\\Boletins\\Boletim-057.jpg");
        System.out.println(resultado);
    }

    public static String processarImagem(String caminhoImagem) {
        ITesseract tesseract = new Tesseract();

        try {
            // Defina o caminho para a pasta tessdata
            tesseract.setDatapath("D:\\Tesseract-OCR\\tessdata");

            // Defina o idioma que deseja usar (ex: "por" para português)
            tesseract.setLanguage("por");

            // Realiza a extração de texto da imagem
            String text = tesseract.doOCR(new File(caminhoImagem));

            // Crie um FileWriter com um nome de arquivo específico
            FileWriter fileWriter = new FileWriter("D:\\Tess4j\\Txt\\Boletim-057.txt");

            // Envolva o FileWriter em BufferedWriter para escrita eficiente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escreva o texto no arquivo
            bufferedWriter.write(text);

            // Feche o BufferedWriter para liberar os recursos
            bufferedWriter.close();

            return "Texto extraído com sucesso!";

        } catch (TesseractException e) {
            return "Erro ao processar a imagem com Tesseract: " + e.getMessage();
        } catch (IOException e) {
            return "Erro ao acessar o arquivo: " + e.getMessage();
        }
    }
}
