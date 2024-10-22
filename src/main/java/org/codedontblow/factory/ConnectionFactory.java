package org.codedontblow.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/docky", "root", "fatec");
        }
        catch(SQLException excecao){
            throw new RuntimeException(excecao);
        }
    }
}