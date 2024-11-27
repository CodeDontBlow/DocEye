package org.codedontblow.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField campoUniqueID, campoNome, campoTelefone, campoEmail, campoEndereco, campoCompetencias, campoIdiomas, campoBuscar;


    //Botões
    @FXML
    private Button btnCadastrar, btnEditar, btnDeletar, btnBuscar;

    //Elementos da tabela
    @FXML
    private TableView<Candidato> contentTable;
    @FXML
    private TableColumn<Candidato, Integer> colunaUniqueID;
    @FXML
    private TableColumn<Candidato, String> colunaNome;
    @FXML
    private TableColumn<Candidato, String> colunaTelefone;
    @FXML
    private TableColumn<Candidato, String> colunaEmail;
    @FXML
    private TableColumn<Candidato, String> colunaEndereco;
    @FXML
    private TableColumn<Candidato, String> colunaCompetencias;
    @FXML
    private TableColumn<Candidato, String> colunaIdiomas;



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



    //Esse metodo serve para inicializar a tabela
    @FXML
    public void initialize() {
        colunaUniqueID.setCellValueFactory(new PropertyValueFactory<>("UniqueID"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("numeroTelefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaCompetencias.setCellValueFactory(new PropertyValueFactory<>("competencias"));
        colunaIdiomas.setCellValueFactory(new PropertyValueFactory<>("idiomas"));


        // Adicionar listener para selecionar linha e preencher campos
        contentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });

        // Carregar dados do banco de dados ao inicializar a tela
        carregarDadosIniciais();

    }


    private void carregarDadosIniciais() {
        List<Candidato> candidatos = candidatoDAO.listarTodos(); // Busca todos os candidatos no banco de dados
        carregarTabela(candidatos); // Carrega os dados na tabela


    }



    //Metodo buscar
    @FXML
    private void Buscar() {
        String filtro = campoBuscar.getText().trim(); // Filtra por qualquer critério

        if (!filtro.isEmpty()) {
            System.out.println("Filtro de busca: " + filtro); // Para depuração
            List<Candidato> candidatos = new ArrayList<>();

            try {
                // Tentativa de buscar por ID
                int id = Integer.parseInt(filtro);
                Candidato candidato = candidatoDAO.buscarPorID(id); // Metodo que busca por ID
                if (candidato != null) {
                    carregarTabela(List.of(candidato));
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("O filtro não é um número. Tentando outros critérios...");
            }

            // Buscar por Nome
            List<Candidato> candidatosPorNome = candidatoDAO.buscarPorNome(filtro);
            if (!candidatosPorNome.isEmpty()) {
                candidatos.addAll(candidatosPorNome);
            }

            // Buscar por Competências
            List<Candidato> candidatosPorCompetencia = candidatoDAO.buscarPorCompetencia(filtro);
            if (!candidatosPorCompetencia.isEmpty()) {
                candidatos.addAll(candidatosPorCompetencia);
            }

            // Buscar por Idiomas
            List<Candidato> candidatosPorIdioma = candidatoDAO.buscarPorIdioma(filtro);
            if (!candidatosPorIdioma.isEmpty()) {
                candidatos.addAll(candidatosPorIdioma);
            }

            // Remover duplicatas (opcional)
            candidatos = candidatos.stream().distinct().toList();

            if (candidatos.isEmpty()) {
                showAlert("Atenção", "Nenhum candidato encontrado com o critério especificado.");
            } else {
                carregarTabela(candidatos); // Carrega a tabela com os resultados
            }
        } else {
            // Caso o campo esteja vazio, lista todos os candidatos
            List<Candidato> candidatos = candidatoDAO.listarTodos();
            carregarTabela(candidatos);
        }
    }


    private void atualizarTabela() {
        // Implemente este metodo para atualizar os dados da tabela contentTable
        List<Candidato> candidato = candidatoDAO.buscarTodos(); // Supondo que exista um metodo buscarTodos()
        contentTable.getItems().clear();
        contentTable.getItems().addAll(candidato);
    }


    private void carregarTabela(List<Candidato> candidatos) {
        ObservableList<Candidato> candidatosObservable = FXCollections.observableArrayList(candidatos);
        contentTable.setItems(candidatosObservable);
    }


    //Os métodos CRUD, como cadastrar, atualizar e deletar estão abaixo
    //Metodo para cadastrar os dados no banco
    public void cadastrar(){
        Candidato candidato = new Candidato();
        candidato.setNome(campoNome.getText());

        if (!campoNome.getText().isBlank()) {
            candidatoDAO.cadastrar(candidato);
            clearFields();  // Limpa os campos após o cadastro
        }

        //Esse metodo deve ser chamada ao final dos métodos CRUD para poder atualizar a lista imediatamente
        //após cada alteração no banco de dados

    }


    // Metodo para atualizar as informações do candidato
    public void atualizar() {
        if (!campoUniqueID.getText().isBlank()) {
            try {
                //Obtém o ID único
                int id = Integer.parseInt(campoUniqueID.getText());

                // Cria um novo objeto candidato com os dados do formulário
                Candidato candidato = new Candidato();
                candidato.setUniqueID(id);
                candidato.setNome(campoNome.getText());
                candidato.setNumeroTelefone(campoTelefone.getText());
                candidato.setEmail(campoEmail.getText());
                candidato.setEndereco(campoEndereco.getText());
                candidato.setCompetencias(campoCompetencias.getText());
                candidato.setIdiomas(campoIdiomas.getText());

                // Chama o metodo para atualizar no DAO
                candidatoDAO.atualizar(candidato);

                // Limpa os campos após a atualização
                clearFields();

                // Mensagem de sucesso (opcional)
                System.out.println("Candidato atualizado com sucesso!");
            } catch (NumberFormatException e) {
                System.err.println("Erro: O campo ID deve conter um número válido.");
            } catch (Exception e) {
                System.err.println("Erro ao atualizar o candidato: " + e.getMessage());
            }
        } else {
            System.err.println("Erro: O campo ID não pode estar vazio.");
        }
    }


    // Metodo para deletar as informações do candidato
// Metodo para deletar as informações do candidato
    public void deletar() {
        String idText = campoUniqueID.getText().trim();

        if (idText.isBlank()) {
            System.out.println("Por favor, insira o ID do candidato para deletar.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Candidato candidato = new Candidato();
            candidato.setUniqueID(id);

            // Chama o DAO para excluir o candidato
            candidatoDAO.deletar(candidato);
            System.out.println("Candidato deletado com sucesso!");

            // Limpa os campos após a exclusão
            clearFields();

            // Atualiza a lista após a exclusão

        } catch (NumberFormatException e) {
            System.out.println("O ID informado não é válido. Insira um número inteiro.");
        } catch (Exception e) {
            System.out.println("Erro ao deletar o candidato: " + e.getMessage());
        }
    }


    // Outros methods

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Limpar campos após as operações de CRUD
    private void clearFields() {
        campoUniqueID.clear();
        campoNome.clear();
        campoBuscar.clear();
    }


    //Serve para preencher os campos de textos com informações ao clicar em uma linha na tabela
    private void preencherCampos(Candidato candidato) {
        campoUniqueID.setText(String.valueOf(candidato.getUniqueID()));
        campoNome.setText(candidato.getNome());
        campoTelefone.setText(String.valueOf(candidato.getNumeroTelefone()));
        campoEmail.setText(String.valueOf(candidato.getEmail()));
        campoEndereco.setText(String.valueOf(candidato.getEndereco()));
        campoCompetencias.setText(candidato.getCompetencias());
        campoIdiomas.setText(candidato.getIdiomas());
    }

}
