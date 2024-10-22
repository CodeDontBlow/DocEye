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


    //Metodos do CRUD
    //Create - Criar

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
        String sql = "UPDATE cliente SET cliente_nome = ? WHERE cliente_codigo = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getTipoDoc());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete - deletar

}
