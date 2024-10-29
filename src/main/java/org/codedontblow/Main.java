package org.codedontblow;

import org.codedontblow.services.InputDocuments;

import static org.codedontblow.services.TesseractOCR.findType;
import static org.codedontblow.services.TesseractOCR.processarImagem;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello CodeDontBlow!");
        InputDocuments i1 = new InputDocuments();

        String filePath = i1.selectFile();
        String text = processarImagem(filePath);  // utiliza o metodo para extrair o texto da imagem
        String doc = findType(text); // utiliza o metodo para retornar se é boletim ou curriculo
        // caso não encontra, retorna null.
        System.out.println(text + doc);
    }
}
