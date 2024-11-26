package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Candidato;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class CandidatoDAO {
    private static Connection connection = null;

    public CandidatoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }


    //Métodos do CRUD
    //Create - Criar
    public void cadastrar(Candidato candidato) {
        String sql = "INSERT INTO candidato (nome, telefone, email, endereco, competencias, idiomas) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getNumeroTelefone());
            stmt.setString(3, candidato.getEmail());
            stmt.setString(4, candidato.getEndereco());
            stmt.setString(5, candidato.getCompetencias());
            stmt.setString(6, candidato.getIdiomas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar candidato: ", e);
        }
    }


    //Update - Atualizar
    public void atualizar(Candidato candidato) {
        String sql = "UPDATE candidato SET nome = ?, telefone = ?, email = ?, endereco = ?, competencias = ?, idiomas = ? WHERE UniqueID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getNumeroTelefone());
            stmt.setString(3, candidato.getEmail());
            stmt.setString(4, candidato.getEndereco());
            stmt.setString(5, candidato.getCompetencias());
            stmt.setString(6, candidato.getIdiomas());
            stmt.setInt(7, candidato.getUniqueID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar candidato: ", e);
        }
    }


    //Delete - deletar
    public void deletar(Candidato candidato) {
        String sql = "DELETE FROM candidato WHERE UniqueID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidato.getUniqueID());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidato: ", e);
        }
    }

    //Esse metodo foi adicionado para poder listar o conteúdo dentro do banco de dados
    //no gerenciador do nosso banco
    public List<Candidato> listarTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                candidatos.add(mapearCandidato(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os candidatos: ", e);
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
                candidato.setUniqueID(rs.getInt("UniqueID"));
                candidato.setNome(rs.getString("nome"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidatos: ", e);
        }
        return candidatos;
    }

    // Metodo auxiliar para mapear o Candidato
    private static Candidato mapearCandidato(ResultSet rs) throws SQLException {
        Candidato candidato_aux = new Candidato();
        candidato_aux.setUniqueID(rs.getInt("UniqueID"));
        candidato_aux.setNome(rs.getString("nome"));
        candidato_aux.setNumeroTelefone(rs.getString("telefone"));
        candidato_aux.setEmail(rs.getString("email"));
        candidato_aux.setEndereco(rs.getString("endereco"));
        candidato_aux.setCompetencias(rs.getString("competencias"));
        candidato_aux.setIdiomas(rs.getString("idiomas"));
        return candidato_aux;
    }




    // Buscar candidatos pelo ID do candidato
    public Candidato buscarPorID(int id) {
        String sql = "SELECT * FROM candidato WHERE UniqueID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearCandidato(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidato por ID: ", e);
        }
        return null;
    }


    // Buscar candidatos pelo nome
    public List<Candidato> buscarPorNome(String nome) {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM candidato WHERE nome LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                candidatos.add(mapearCandidato(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidatos por nome: ", e);
        }
        return candidatos;
    }


    public List<Candidato> buscarTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM Candidato";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setUniqueID(rs.getInt("uniqueID"));
                candidato.setNome(rs.getString("nome"));
                candidato.setNumeroTelefone(rs.getString("numeroTelefone"));
                candidato.setEmail(rs.getString("email"));
                candidato.setEndereco(rs.getString("endereco"));
                candidato.setCompetencias(rs.getString("competencias"));
                candidato.setIdiomas(rs.getString("idiomas"));

                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os candidatos: ", e);
        }

        return candidatos;
    }






}
