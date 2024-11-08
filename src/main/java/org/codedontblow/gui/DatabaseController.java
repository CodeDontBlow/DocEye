package org.codedontblow.gui;

import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.model.Candidato;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;

//Essa classe Controller terá métodos mais complexos, foi criada separadamente do controle de banco de dados para evitar que métodos simples se misturassem com métodos mais difíceis
//Esse controller controlará o arquivo FXML referente ao Banco de Dados
public class DatabaseController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Adicionando variáveis do banco de dados (textField, buttons etc)
    @FXML
    private Label labelCandidato;


    //Campos de texto
    @FXML
    private TextField campoID_candidato;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoTipo_doc;

    @FXML
    private TextField campoBuscar;

    //Botões
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    //Declaração do objeto candidatoDAO
    private final CandidatoDAO candidatoDAO = new CandidatoDAO();


    //Troca para a tela de Entrada de Arquivos
    public void trocaTela2(ActionEvent click) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/codedontblow/EntradaArquivos.fxml"));
        stage = (Stage)((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Os métodos CRUD estão abaixo
    //Metodo para cadastrar os dados no banco
    public void cadastrar(){
        Candidato candidato = new Candidato();
        candidato.setNome(campoNome.getText());
        candidato.setTipoDoc(campoTipo_doc.getText());

        if (!campoNome.getText().isBlank() && !campoTipo_doc.getText().isBlank()) {
            candidatoDAO.cadastrar(candidato);
            clearFields();  // Limpa os campos após o cadastro
        }
    }


    // Metodo para atualizar as informações do candidato
    public void atualizar() {
        if (!campoID_candidato.getText().isBlank()) {
            int id = Integer.parseInt(campoID_candidato.getText());
            Candidato candidato = new Candidato();
            candidato.setUniqueIDCandidato(id);
            candidato.setNome(campoNome.getText());
            candidato.setTipoDoc(campoTipo_doc.getText());

            candidatoDAO.atualizar(candidato);
            clearFields();  // Limpa os campos após a atualização
        }
    }


    // Metodo para deletar as informações do candidato
    public void deletar() {
        if (!campoID_candidato.getText().isBlank() || !campoNome.getText().isBlank()) {

            int id = Integer.parseInt(campoID_candidato.getText());
            Candidato candidato = new Candidato();
            candidato.setUniqueIDCandidato(id);

            candidatoDAO.deletar(candidato);
            clearFields();  // Limpa os campos após a exclusão
        }
    }


    // Limpar campos após as operações de CRUD
    private void clearFields() {
        campoID_candidato.clear();
        campoNome.clear();
        campoTipo_doc.clear();
        campoBuscar.clear();
    }


}
