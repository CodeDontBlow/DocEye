package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Boletim;
import org.codedontblow.model.Curriculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurriculoDAO {

    private final Connection connection;

    public CurriculoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    //Metodos do CRUD
//Create - Criar
    public void cadastrar(Curriculo curriculo) {
        String sql = "INSERT INTO curriculo (UniqueID, telefone, email, linkedin, portifolio, endereco, competencias, idiomas) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, curriculo.getUniqueIDCurriculo());
            stmt.setString(2, curriculo.getNumeroTelefone());
            stmt.setString(3, curriculo.getEmail());
            stmt.setString(4, curriculo.getLinkedin());
            stmt.setString(6, curriculo.getPortifolio());
            stmt.setString(7, curriculo.getEndereco());
            stmt.setString(8, curriculo.getCompetencias());
            stmt.setString(9, curriculo.getIdiomas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
