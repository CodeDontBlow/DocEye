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

    //Método para atualizar candidatos
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

    //Método para deletar candidatos
    public void deletar(Candidato candidato) {
        String sql = "DELETE FROM candidato WHERE UniqueID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, candidato.getUniqueID());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar candidato: ", e);
        }
    }

    //Método para selecionar todos os candidatos
    public List<Candidato> selecionarTodos() {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT * FROM Candidato";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                candidatos.add(mapearCandidato(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os candidatos: ", e);
        }

        return candidatos;
    }

    //Método para a busca de candidatos por requisitos
    public List<Candidato> buscarRequisitos(String sql, String[] requisitos, int numeroRequisitos){
        List<Candidato> candidatos = new ArrayList<>(); // Iniciando a lista de candidatos que atendem os requisitos

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //Loop de repetição que busca todos os requisitos de acordo com a quantidade deles
            for(int i = 0 ; i < numeroRequisitos ; i++) {
                stmt.setString(i * 2 + 1, "%" + requisitos[i].trim()+ "%"); //Usa uma função para preencher um campo do stmt
                stmt.setString(i * 2 + 2, "%" + requisitos[i].trim()+ "%"); //Usa outra função com o mesmo propósito
                //As funções são usadas para garantir que dois atributos iguais apareçam no sql
                //A primeira e a segunda aparição de '?' no sqlRes devem conter o mesmo requisito. Portanto, quando i = 0, o esperado é:
                //stmt.setString(1, requisitos[0]) e stmt.setString(2, requisitos[0]) --> a função faz isso acontecer
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                candidatos.add(mapearCandidato(rs));
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao buscar o candidato por requisito: ", e);
        }
        return candidatos;
    }

    // Metodo auxiliar para selecionar Candidatos
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

}
