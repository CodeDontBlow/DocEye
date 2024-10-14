package org.codedontblow.dao;

import org.codedontblow.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//!!! FALTA RELACIONAR OS ID'S !!!
public class InsertSQL{

    //Recebe como parâmetros de entrada o output fornecido pelo moondream e o tipo de documento que será cadastrado
    public static void insereOutput(String moondreamOutput, String tipoDoc) throws SQLException {

        //Inicia uma conexão com o Banco de Dados e a chama de "conn"
        Connection conn = new ConnectionFactory().getConnection();

        //Define o código SQL para inserção na tabela "Candidato"
        String sqlCandidato = "INSERT INTO candidato (nome, tipo_doc) VALUES (?, ?)";
        PreparedStatement stmtCandidato = conn.prepareStatement(sqlCandidato);

        //Define o código SQL para inserção na tabela "Boletim"
        String sqlBoletim = "INSERT INTO boletim (matricula, escola, nota_port, nota_mat) VALUES (?, ?, ?, ?)";
        PreparedStatement stmtBoletim = conn.prepareStatement(sqlBoletim);

        //Define o código SQL para inserção na tabela "Curriculo"
        String sqlCurriculo = "INSERT INTO curriculo (telefone, email, linkedin, portifolio, endereco, competencias, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmtCurriculo = conn.prepareStatement(sqlCurriculo);

        try {
            //Tratamento do output do Moondream. Separando as linhas
            String[] linhas = moondreamOutput.split("/");

            //Tratamento do output do Moondream. Separando os atributos de cada linha
            for (String linha : linhas) {
                String[] atributos = linha.split(",");

                stmtCandidato.setString(1, atributos[0]);
                stmtCandidato.setString(2, tipoDoc);
                stmtCandidato.addBatch();

                //Criando o código SQL para inserir todos os atributos presentes no output dentro da tabela boletim, caso o tipo de documento seja um boletim.
                if (tipoDoc.equals("Boletim")) {

                    stmtBoletim.setString(1, atributos[1]); //Número da Matrícula
                    stmtBoletim.setString(2, atributos[2]); //Nome da Escola
                    stmtBoletim.setString(3, atributos[3]); //Nota de Português
                    stmtBoletim.setString(4, atributos[4]); //Nota de Matemática
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
                    stmtCurriculo.addBatch(); //Armazena os comandos sql em um lote que será executado em breve
                }
            }

            //Inserindo no Banco de Dados

            stmtCandidato.executeBatch();
            stmtCandidato.close();
            System.out.println("Candidato Cadastrado!");

            //Executa os códigos SQL, armazenados no lote, referentes a tabela boletim
            if (tipoDoc.equals("Boletim")) {
                stmtBoletim.executeBatch();
                stmtBoletim.close();
                System.out.println("Inserção de Boletim Bem Sucedida!");
            }

            //Executa os códigos SQL, armazenados no lote, referentes a tabela curriculo
            else if (tipoDoc.equals("Curriculo")){
                stmtCurriculo.executeBatch();
                stmtCurriculo.close();
                System.out.println("Inserção de Curriculo Bem Sucedida!");
            }
        }

        catch(SQLException u) {
            throw new RuntimeException(u);
        }

    }
}