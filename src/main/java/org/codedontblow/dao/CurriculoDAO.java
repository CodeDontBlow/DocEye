package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Boletim;
import org.codedontblow.model.Candidato;
import org.codedontblow.model.Curriculo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurriculoDAO {
    private final Connection connection;

    public CurriculoDAO(Connection connection) {
        this.connection = connection;
    }





    //Update - Atualizar
    public void atualiza(Curriculo curriculo) {
        String sql = "UPDATE curriculo SET telefone = ?, email = ?, linkedin = ?, portifolio = ?, endereco = ?, competencias = ?, idiomas = ? WHERE UniqueID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curriculo.getNumeroTelefone());
            stmt.setString(2, curriculo.getEmail());
            stmt.setString(3, curriculo.getLinkedin());
            stmt.setString(4, curriculo.getPortifolio());
            stmt.setString(5, curriculo.getEndereco());
            stmt.setString(6, curriculo.getCompetencias());
            stmt.setString(7, curriculo.getIdiomas());
            stmt.setInt(8, curriculo.getUniqueIDCurriculo());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
