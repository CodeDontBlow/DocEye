package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertSQL{

    //Recebe como parâmetros de entrada o output fornecido pelo moondream e o tipo de documento que será cadastrado
    public static void insereOutput(String moondreamOutput, String tipoDoc) throws SQLException {

        //Inicia uma conexão com o Banco de Dados e a chama de "conn"
        Connection conn = new ConnectionFactory().getConnection();

        //Define o código SQL para inserção na tabela "Candidato"
        String sqlCandidato = "INSERT INTO candidato (nome, tipo_doc) VALUES (?, ?)";
        PreparedStatement stmtCandidato = conn.prepareStatement(sqlCandidato, PreparedStatement.RETURN_GENERATED_KEYS);

        //Define o código SQL para inserção na tabela "Boletim"
        String sqlBoletim = "INSERT INTO boletim (matricula, escola, nota_port, nota_mat, UniqueID) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmtBoletim = conn.prepareStatement(sqlBoletim);

        //Define o código SQL para inserção na tabela "Curriculo"
        String sqlCurriculo = "INSERT INTO curriculo (telefone, email, linkedin, portifolio, endereco, competencias, idiomas, UniqueID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmtCurriculo = conn.prepareStatement(sqlCurriculo);

        try {
            //Tratamento do output do Moondream. Separando as linhas
            String[] linhas = moondreamOutput.split("/");

            //Tratamento do output do Moondream. Separando os atributos de cada linha
            for (String linha : linhas) {
                String[] atributos = linha.split(",");

                stmtCandidato.setString(1, atributos[0]); //Nome do Candidato
                stmtCandidato.setString(2, tipoDoc); //Tipo de Documento do Candidato
                stmtCandidato.executeUpdate(); //Executando a Inserção dos Dados do Candidato

                //Checando o ID (Chave) do Candidato
                String candidatoID = ""; //Variável que receberá o valor do ID
                ResultSet busca = stmtCandidato.getGeneratedKeys(); //Busca pelas chaves geradas automaticamente
                //Verifica se há ao menos uma chave criada
                if(busca.next()){
                    candidatoID = String.valueOf(busca.getString(1)); //Adiciona o valor da chave à variável candidatoID
                }

                //Criando o código SQL para inserir todos os atributos presentes no output dentro da tabela boletim, caso o tipo de documento seja um boletim.
                if (tipoDoc.equals("Boletim")) {
                    stmtBoletim.setString(1, atributos[1]); //Número da Matrícula
                    stmtBoletim.setString(2, atributos[2]); //Nome da Escola
                    stmtBoletim.setString(3, atributos[3]); //Nota de Português
                    stmtBoletim.setString(4, atributos[4]); //Nota de Matemática
                    stmtBoletim.setString(5, candidatoID);  //UniqueID
                    stmtBoletim.addBatch(); //Armazena os comandos sql em um lote que será executado em breve
                }

                //Criando o código SQL para inserir todos os atributos presentes no output dentro da tabela curriculo, caso o tipo de documento seja um currículo.
                else if (tipoDoc.equals("Curriculo")){
                    stmtCurriculo.setString(1, atributos[1]); //telefone
                    stmtCurriculo.setString(2, atributos[2]); //email
                    stmtCurriculo.setString(3, atributos[3]); //linkedin
                    stmtCurriculo.setString(4, atributos[4]); //portifolio
                    stmtCurriculo.setString(5, atributos[5]); //endereço
                    stmtCurriculo.setString(6, atributos[6]); //competencias
                    stmtCurriculo.setString(7, atributos[7]); //idiomas
                    stmtCurriculo.setString(7, atributos[7]); //idiomas
                    stmtCurriculo.setString(8, candidatoID);  //UniqueID
                    stmtCurriculo.addBatch(); //Armazena os comandos sql em um lote que será executado em breve
                }
            }

            //Fechando a conexão com a tabela Candidato
            stmtCandidato.close();
            System.out.println("Candidatos Cadastrado!");

            //Executa os códigos SQL, armazenados no lote, referentes a tabela boletim
            if (tipoDoc.equals("Boletim")) {
                stmtBoletim.executeBatch();
                stmtBoletim.close();
                System.out.println("Inserção de Boletins Bem Sucedida!");
            }

            //Executa os códigos SQL, armazenados no lote, referentes a tabela curriculo
            else if (tipoDoc.equals("Curriculo")){
                stmtCurriculo.executeBatch();
                stmtCurriculo.close();
                System.out.println("Inserção de Curriculos Bem Sucedida!");
            }
        }

        catch(SQLException u) {
            throw new RuntimeException(u);
        }

    }
}