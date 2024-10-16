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
        ITesseract tesseract = new Tesseract();

        try {
            // Defina o caminho para a pasta tessdata
            tesseract.setDatapath("D:\\Tesseract-OCR\\tessdata");

            // Defina o idioma que deseja usar (ex: "por" para português)
            tesseract.setLanguage("por"); //

            // Caminho da imagem
            String text = tesseract.doOCR(new File("D:\\Tess4j\\Boletins\\Boletim-020.jpg"));
            System.out.println(text);

            // Crie um FileWriter com um nome de arquivo específico
            FileWriter fileWriter = new FileWriter("D:\\Tess4j\\Txt\\Boletim-020.txt");

            // Envolva o FileWriter em BufferedWriter para escrita eficiente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escreva o texto no arquivo
            bufferedWriter.write(text);

            // Feche o BufferedWriter para liberar os recursos
            bufferedWriter.close();

            System.out.println("Texto extraído com sucesso!");

        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
    }
}
