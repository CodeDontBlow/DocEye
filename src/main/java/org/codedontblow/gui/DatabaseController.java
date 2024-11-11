package org.codedontblow.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.dao.CurriculoDAO;
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
import org.codedontblow.model.Curriculo;

import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private Button btnBuscar;

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



    //Esse metodo serve para mostrar o banco de dados na lista assim que o usuário entra no gerenciamento
    //do banco
    @FXML
    public void initialize() {
        carregarCandidatos();
    }






    // Declaração do ListView. O ID do List View é listViewCandidatos
    @FXML
    private ListView<String> listViewCandidatos;



    //Metodo para carregar todos os candidatos no list View
    public void carregarCandidatos() {
        List<Candidato> candidatos = candidatoDAO.listarTodos();
        listViewCandidatos.getItems().clear(); // Limpa a lista antes de adicionar os novos itens

        for (Candidato candidato : candidatos) {
            String infoCandidato = "ID: " + candidato.getUniqueIDCandidato() +
                    " | Nome: " + candidato.getNome() +
                    " | Tipo Doc: " + candidato.getTipoDoc();
            listViewCandidatos.getItems().add(infoCandidato);
        }
    }





    //Metodo para buscar um candidato específico no banco de dados
    public void buscar() {
        String query = campoBuscar.getText();
        List<Candidato> candidatos = candidatoDAO.buscarCandidatos(query);

        listViewCandidatos.getItems().clear(); // Limpa a lista atual

        for (Candidato candidato : candidatos) {
            String info = "ID: " + candidato.getUniqueIDCandidato() + ", Nome: " + candidato.getNome() + ", Tipo Doc: " + candidato.getTipoDoc();
            listViewCandidatos.getItems().add(info); // Adiciona o candidato no ListView
        }
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

        //Esse metodo deve ser chamada ao final dos métodos CRUD para poder atualizar a lista imediatamente
        //após cada alteração no banco de dados
        carregarCandidatos();
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

        //Esse metodo deve ser chamada ao final dos métodos CRUD para poder atualizar a lista imediatamente
        //após cada alteração no banco de dados
        carregarCandidatos();
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

        //Esse metodo deve ser chamada ao final dos métodos CRUD para poder atualizar a lista imediatamente
        //após cada alteração no banco de dados
        carregarCandidatos();
    }


    // Limpar campos após as operações de CRUD
    private void clearFields() {
        campoID_candidato.clear();
        campoNome.clear();
        campoTipo_doc.clear();
        campoBuscar.clear();
    }


}
