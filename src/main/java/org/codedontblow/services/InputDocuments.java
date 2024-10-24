package org.codedontblow.services;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InputDocuments extends Application {

    private String selectedFilePath;

    @Override
    public void start(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Selecione um arquivo");

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            selectedFilePath = selectedFile.getAbsolutePath();
        }

        // Fechar o stage depois de selecionar o arquivo
        stage.close();
    }

    public String selectFile() {
        launch();
        return selectedFilePath;
    }

    public static void main(String[] args) {
        InputDocuments i1 = new InputDocuments();
        String filePath = i1.selectFile();
        System.out.println("Arquivo selecionado: " + filePath);
    }
}
