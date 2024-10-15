package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Boletim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoletimDAO {
    private final Connection connection;

    public BoletimDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }


    //Delete
    public void deletar(Boletim boletim) {
        String sql = "DELETE FROM boletim WHERE UniqueID = ? AND matricula = ? AND escola = ? AND nota_port = ? AND nota_mat = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, boletim.getUniqueIDBoletim());
            stmt.setString(2, boletim.getMatricula());
            stmt.setString(3, boletim.getEscola());
            stmt.setDouble(4, boletim.getNotaPortugues());
            stmt.setDouble(5, boletim.getNotaMatematica());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
