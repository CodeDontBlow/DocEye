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
