package org.codedontblow.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

//Essa classe Controller terá métodos mais simples, foi criada separadamente do controle de banco de dados para evitar que métodos simples se misturassem com métodos mais difíceis
//Esse controller controlará os arquivos FXML referentes a Tela Inicial e a Entrada de Arquivos.
public class SimpleController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    //Botao Entrar
    public void trocaTela1(ActionEvent click) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        stage = (Stage)((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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



}
