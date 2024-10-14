package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
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




    //Update - Atualizar
    public void atualiza(Boletim boletim) {
        String sql = "UPDATE boletim SET matricula = ?, escola = ?, nota_port = ?, nota_mat = ? WHERE UniqueID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, boletim.getMatricula());
            stmt.setString(2, boletim.getEscola());
            stmt.setDouble(3, boletim.getNotaPortugues());
            stmt.setDouble(4, boletim.getNotaMatematica());
            stmt.setInt(5, boletim.getUniqueIDBoletim());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
