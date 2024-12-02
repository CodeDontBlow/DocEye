package org.codedontblow.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //Senha usada: kali
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/docky", "root", "bdLapms@1711");
        }
        catch(SQLException excecao){
            throw new RuntimeException(excecao);
        }
    }
}
