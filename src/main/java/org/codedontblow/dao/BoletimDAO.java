package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Boletim;
import org.codedontblow.model.Candidato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoletimDAO {

    private final Connection connection;

    public BoletimDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    //Metodos do CRUD
    //Create - Criar
    public void cadastrar(Boletim boletim) {
        String sql = "INSERT INTO boletim (UniqueID, matricula, escola, nota_port, nota_mat) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, boletim.getUniqueIDBoletim());
            stmt.setString(2, boletim.getMatricula());
            stmt.setString(3, boletim.getEscola());
            stmt.setDouble(4, boletim.getNotaPortugues());
            stmt.setDouble(5, boletim.getNotaMatematica());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
