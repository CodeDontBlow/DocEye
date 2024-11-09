package org.codedontblow.gui;

import io.github.ollama4j.exceptions.OllamaBaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;

//Essa classe Controller terá métodos mais simples, foi criada separadamente do controle de banco de dados para evitar que métodos simples se misturassem com métodos mais difíceis
//Esse controller controlará os arquivos FXML referentes a Tela Inicial e a Entrada de Arquivos.
public class SimpleController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //Troca para a tela de Entrada de Arquivos
    public void trocaTela2(ActionEvent click) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EntradaArquivos.fxml"));
        stage = (Stage)((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Troca para a tela de Banco de Dados
    public void trocaTela3(ActionEvent click) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/codedontblow/BancoDados.fxml"));
        stage = (Stage)((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextArea outputMessage;
    //Metodo antigo de selecionamento de arquivos
//    public void selecionaArquivos(ActionEvent click) throws OllamaBaseException, IOException, InterruptedException {
//        InputDocuments explorerUI = new InputDocuments();
//        String filePath = explorerUI.selectFile();
//        outputMessage.setText("Extraindo Informações do Arquivo: " +filePath);
//
//        String dockyOutput = OllamaApi.processFile(filePath);
//
//    }


    //Novo metodo de selecionamento de arquivos com a tela do windows explorer
    public void selecionaArquivos(ActionEvent click) throws OllamaBaseException, IOException, InterruptedException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o Arquivo");

        // Configura filtro de extensão para facilitar a navegação
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Documentos", "*.pdf", "*.jpg"),
                new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*")
        );

        // Abre o FileChooser
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            outputMessage.setText("Extraindo Informações do Arquivo: " + filePath);
            // Aqui você pode chamar métodos para processar o arquivo
            String dockyOutput = OllamaApi.processFile(filePath);
        } else {
            outputMessage.setText("Nenhum arquivo selecionado.");
        }
    }





}
