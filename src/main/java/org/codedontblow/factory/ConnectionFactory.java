package org.codedontblow.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //Senha usada: fatec
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/docky", "root", "sua_senha_banco_dados");
        }
        catch(SQLException excecao){
            throw new RuntimeException(excecao);
        }
    }
}
