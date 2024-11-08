package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Candidato;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;


public class CandidatoDAO {
    private final Connection connection;

    public CandidatoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }


    //Métodos do CRUD
    //Create - Criar
    public void cadastrar(Candidato candidato) {
        String sql = "INSERT INTO candidato (UniqueID, nome, tipo_doc) VALUES(?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, candidato.getUniqueIDCandidato());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, candidato.getTipoDoc());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Read - ler
    public void ler() {
        String sql = "SELECT * FROM candidato";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("ID       | Nome      | Tipo Doc");
            System.out.println("-------------------------------");

            while (rs.next()) {
                int id = rs.getInt("UniqueID");
                String nome = rs.getString("nome");
                String tipoDoc = rs.getString("tipo_doc");

                System.out.printf("%-8d | %-9s | %-10s%n", id, nome, tipoDoc);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler dados: ", e);
        }
    }



    //Update - Atualizar
    public void atualizar(Candidato candidato) {
        String sql = "UPDATE candidato SET nome = ?, tipo_doc = ? WHERE UniqueID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getTipoDoc());
            stmt.setInt(3, candidato.getUniqueIDCandidato());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tabela candidato: ", e);
        }
    }

    //Delete - deletar
    public void deletar(Candidato candidato){
        String sql = "DELETE FROM candidato WHERE UniqueID = ? OR nome = ? OR tipo_doc = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, candidato.getUniqueIDCandidato());
            stmt.setString(2, candidato.getNome());
            stmt.setString(3, candidato.getTipoDoc());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
