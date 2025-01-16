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

import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.util.Objects;

//Essa classe Controller terá métodos mais complexos, foi criada separadamente do controle de banco de dados para evitar que métodos simples se misturassem com métodos mais difíceis
//Esse controller controlará o arquivo FXML referente ao Banco de Dados
public class DatabaseController {

    //Adicionando variáveis do banco de dados (textField, buttons etc)
    @FXML
    private Label labelCandidato;

    //Campos de texto
    @FXML
    private TextField campoNome, campoTelefone, campoEmail, campoEndereco, campoCompetencias, campoIdiomas, campoBuscar;
    private int campoUniqueID; //Variável para o id do usuario

    //Botões
    @FXML
    private Button btnCadastrar, btnEditar, btnDeletar, btnBuscar;

    @FXML
    //Textarea para o output
    private TextArea textareaOutput;

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/codedontblow/EntradaArquivos.fxml")));
        Stage stage = (Stage) ((Node) click.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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
        carregarTabela();
    }

    //Exibe o banco de daods na tabela
    private void carregarTabela() {
        // Implemente este metodo para atualizar os dados da tabela contentTable
        List<Candidato> candidato = candidatoDAO.selecionarTodos(); // Supondo que exista um metodo buscarTodos()
        contentTable.getItems().clear();
        contentTable.getItems().addAll(candidato);
    }

    //Carrega a tabela com candidatos específicos (usado para filtragem)
    private void atualizarTabela(List<Candidato> candidatos) {
        ObservableList<Candidato> candidatosObservable = FXCollections.observableArrayList(candidatos);
        contentTable.setItems(candidatosObservable);
    }

    //Busca candidatos por requisitos
    @FXML
    private void buscarCandidato() {
        String filtro = campoBuscar.getText().trim(); // Filtro do usuario
        System.out.println("Filtro de busca: " + filtro); // Para depuração

        //Se o filtro estiver vazio, lista todos os candidatos do banco
        if (filtro.isEmpty()){
            carregarTabela();
        }

        else {
            String[] requisitos = filtro.split(","); //Cria a Array de requisitos
            int maxRequisitos = 5; //Define o máximo de 5 requisitos
            int numeroRequisitos = Math.min(requisitos.length, maxRequisitos); //Define a quantidade de requisitos que serão buscados, com um valor máximo
            System.out.println("Quantidade de requisitos: " + numeroRequisitos); //Depuração

            //Se o usuario escrever mais que o máximo de requisitos
            if (requisitos.length > maxRequisitos) {
                System.out.println("Muitos requisitos. Considerando somente os " + maxRequisitos + " primeiros");
            }

            String[] requisitosRes = Arrays.copyOf(requisitos, numeroRequisitos);//Cria um Array novo somente com a quantidade certa de requisitos (importante para caso o usuario digite menos ou mais requisitos do que o valor máximo)
            StringBuilder sqlRes = new StringBuilder("SELECT * FROM candidato WHERE"); //Cria o código sql

            //Adicionando valores ao código sql para cumprir a demanda de requisitos
            for (int i = 0; i < numeroRequisitos; i++) {
                if (i > 0) sqlRes.append(" AND");
                sqlRes.append(" (competencias LIKE ? OR idiomas LIKE ?)");
            }
            //Chama envia o sql, os requisitos e a quantidade deles para o método buscarRequisitos
            List<Candidato> candidatosPorRequisitos = candidatoDAO.buscarRequisitos(sqlRes.toString(), requisitosRes, numeroRequisitos);

            //Se nenhum usuario atender aos requisitos, exibe a tabela toda
            if(candidatosPorRequisitos.isEmpty()) {
                System.out.println("Nenhum candidato encontrado");
                textareaOutput.setText("Nenhum candidato foi encontrado com os requisitos fornecidos. Exibindo todos os candidatos.");
                carregarTabela();
            }
            //Se não, carrega a tabela com os candidatos que se enquadraram
            else{
                System.out.println("Busca realizada");
                textareaOutput.setText("Exibindo candidatos que se enquadrem nos requisitos!");
                atualizarTabela(candidatosPorRequisitos);
            }

        }
    }

    //Os métodos CRUD, atualizar e deletar estão abaixo
    // Metodo para atualizar as informações do candidato
    public void atualizarCandidato() {
        try {
            //Obtém o ID único
            int id = campoUniqueID;

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
            limparCampos();
            carregarTabela();

            // Mensagens de sucesso
            System.out.println("Candidato atualizado com sucesso!");
            textareaOutput.setText("Candidato " + candidato.getNome() + " atualizado com sucesso!");
        }
        catch (Exception e) {
            System.err.println("Erro ao atualizar o candidato: " + e.getMessage());
            textareaOutput.setText("Algo deu errado na atualização deste candidato");
        }

    }

    // Metodo para deletar as informações do candidato
    public void deletarCandidato() {
        int id = campoUniqueID;

        Candidato candidato = new Candidato();
        candidato.setUniqueID(id);

        try{
            // Chama o DAO para excluir o candidato
            candidatoDAO.deletar(candidato);
            textareaOutput.setText("Candidato deletado com sucesso!");

            // Limpa os campos após a exclusão
            limparCampos();
            carregarTabela();
        }

        catch (Exception e) {
            System.out.println("Erro ao deletar o candidato: " + e.getMessage());
            textareaOutput.setText("Algo deu errado na atualização deste candidato");
        }
    }

    // Outros Métodos
    // Limpar campos após as operações de CRUD
    private void limparCampos() {
        campoNome.clear();
        campoTelefone.clear();
        campoEmail.clear();
        campoEndereco.clear();
        campoCompetencias.clear();
        campoIdiomas.clear();
    }

    //Serve para preencher os campos de textos com informações ao clicar em uma linha na tabela
    private void preencherCampos(Candidato candidato) {
        campoUniqueID = candidato.getUniqueID();
        campoNome.setText(candidato.getNome());
        campoTelefone.setText(String.valueOf(candidato.getNumeroTelefone()));
        campoEmail.setText(String.valueOf(candidato.getEmail()));
        campoEndereco.setText(String.valueOf(candidato.getEndereco()));
        campoCompetencias.setText(candidato.getCompetencias());
        campoIdiomas.setText(candidato.getIdiomas());
    }

}
