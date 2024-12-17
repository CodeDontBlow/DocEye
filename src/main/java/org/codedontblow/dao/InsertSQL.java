package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertSQL{

    //Recebe como parâmetros de entrada o output fornecido pelo moondream e o tipo de documento que será cadastrado
    public static void insereOutput(String dockyOutput) throws SQLException {

        //Inicia uma conexão com o Banco de Dados e a chama de "conn"
        Connection conn = new ConnectionFactory().getConnection();

        dockyOutput = dockyOutput + ";";

        //Define o código SQL para inserção na tabela "Curriculo"
        String sql = "INSERT INTO candidato (nome, telefone, email, endereco, competencias, idiomas) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            //Tratamento do output do Llava. Separando as linhas
            String[] linhas = dockyOutput.split("/");

            //Tratamento do output do Llava. Separando os atributos de cada linha
            for (String linha : linhas) {
                String[] atributos = linha.split(";");

                //Criando o código SQL para inserir todos os atributos presentes no output dentro da tabela curriculo, caso o tipo de documento seja um currículo.
                    stmt.setString(1, atributos[0]); //Nome
                    stmt.setString(2, atributos[1]); //telefone
                    stmt.setString(3, atributos[2]); //email
                    stmt.setString(4, atributos[3]); //endereço
                    stmt.setString(5, atributos[4]); //competencias
                    stmt.setString(6, atributos[5]); //idiomas
                    stmt.executeUpdate();
            }
            //Fechando a conexão com a tabela Candidato
            stmt.close();
            conn.close();

        }

        catch(SQLException u) {
            throw new RuntimeException(u);
        }

    }
}
