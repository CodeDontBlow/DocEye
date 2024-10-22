package org.codedontblow.dao;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.factory.ConnectionFactory;
import org.codedontblow.model.Boletim;
import org.codedontblow.model.Candidato;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private final Connection connection;

    public BoletimDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    //Read - ler
    public void ler() {
        String sql = "SELECT * FROM boletim";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("ID       | Matricula | Escola               | Nota Português | Nota Matemática ");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("UniqueID");
                String matricula = rs.getString("matricula");
                String escola = rs.getString("escola");
                Float nota_port = rs.getFloat("nota_port");
                Float nota_mat = rs.getFloat("nota_mat");

                System.out.printf("%-8d | %-9s | %-20s | %-14f | %-5f%n", id, matricula, escola, nota_port, nota_mat);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler dados: ", e);
        }
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
