package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Candidato;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class CandidatoDAO {
    private final Connection connection;

    public CandidatoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }


    //Métodos do CRUD
    //Create - Criar
    public void cadastrar(Candidato candidato) {
        String sql = "INSERT INTO candidato (UniqueID, nome) VALUES(?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, candidato.getUniqueIDCandidato());
            stmt.setString(2, candidato.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Update - Atualizar
    public void atualizar(Candidato candidato) {
        String sql = "UPDATE candidato SET nome = ? WHERE UniqueID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setInt(2, candidato.getUniqueIDCandidato());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tabela candidato: ", e);
        }
    }

    //Delete - deletar
    public void deletar(Candidato candidato){
        String sql = "DELETE FROM candidato WHERE UniqueID = ? OR nome = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, candidato.getUniqueIDCandidato());
            stmt.setString(2, candidato.getNome());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Esse metodo foi adicionado para poder listar o conteúdo dentro do banco de dados
    //no gerenciador do nosso banco
    public List<Candidato> listarTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT UniqueID, nome FROM candidato";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setUniqueIDCandidato(rs.getInt("UniqueID"));
                candidato.setNome(rs.getString("nome"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidatos: ", e);
        }

        return candidatos;
    }






    //Metodo para buscar um candidato específico e mostrá-lo no listView
    public List<Candidato> buscarCandidatos(String query) {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato WHERE nome LIKE ? OR UniqueID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            try {
                stmt.setInt(2, Integer.parseInt(query)); // Tenta converter a busca para int (para buscar pelo ID)
            } catch (NumberFormatException e) {
                stmt.setInt(2, -1); // Caso não seja um número, evita erro na query
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setUniqueIDCandidato(rs.getInt("UniqueID"));
                candidato.setNome(rs.getString("nome"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidatos: ", e);
        }

        return candidatos;
    }

}
