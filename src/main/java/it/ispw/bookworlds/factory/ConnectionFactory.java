package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.utils.ApplicationProperties;
import it.ispw.bookworlds.utils.Printer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection instance = null;

    private ConnectionFactory(){}

    public static Connection getInstance(){
        if(instance == null){
            try{
                String connectionUrl = ApplicationProperties.getProperty("connection_url");
                String username = ApplicationProperties.getProperty("database_username");
                String password = ApplicationProperties.getProperty("database_password");

                instance = DriverManager.getConnection(connectionUrl, username, password);
            } catch (SQLException e) {
                Printer.printError(e.getLocalizedMessage());
                System.exit(-1);
            }
        }
        return instance;
    }
}
