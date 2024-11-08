package org.codedontblow.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;

public class TesseractOCR {

    public static String processarImagem(String filePath) {
        ITesseract tesseract = new Tesseract();

        try {
            // Defina o caminho para a pasta tessdata
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");


            // Realiza a extração de texto da imagem
            String txt = tesseract.doOCR(new File(filePath));


            return txt;

        } catch (TesseractException e) {
            return "Erro ao processar a imagem com Tesseract: " + e.getMessage();
        }
    }

    public static String findType(String tessOutput) {
        tessOutput = tessOutput.toLowerCase();
        String documento = null;

        String[] boletimWords = {"boletim", "disciplinas", "subject",};
        String[] curriculoWords = {"@", "contato", "idiomas", "experiencia", "formacao"};

        for (String i : boletimWords) {
            if (tessOutput.contains(i)) {
                documento = "Boletim";
                break;
            }
        }
        if (documento == null) {
            for (String j : curriculoWords) {
                if (tessOutput.contains(j)) {
                    documento = "Curriculo";
                    break;
                }
            }
        }
        return documento;
    }
}
