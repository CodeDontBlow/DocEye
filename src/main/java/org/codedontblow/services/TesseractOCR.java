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
            tesseract.setDatapath("D:\\Tesseract-OCR\\tessdata");

            return tesseract.doOCR(new File(filePath));

        } catch (TesseractException e) {
            return "Erro ao processar a imagem com Tesseract: " + e.getMessage();
        }
    }
}
