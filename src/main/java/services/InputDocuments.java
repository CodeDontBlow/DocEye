package services;

import javax.swing.*;
import java.io.File;

public class InputDocuments {
    public String selectFile(){
        JFrame frame = new JFrame("Selecione um arquivo: ");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(frame);

        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            return  selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }
}
