package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscribersDAOJDBC implements SubscribersDAO{
    private static SubscribersDAOJDBC instance = null;

    private SubscribersDAOJDBC(){}

    public static SubscribersDAOJDBC getInstance(){
        if(instance == null) instance = new SubscribersDAOJDBC();
        return instance;
    }

    @Override
    public boolean isSubscriber(String reader, String bookClub) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT bookclub, reader FROM subscribers WHERE bookclub=? AND reader=?");
            statement.setString(1, bookClub);
            statement.setString(2, reader);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) return true;
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return false;
    }

    @Override
    public void addSubscriber(String bookClub, String reader) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO subscribers VALUES (?,?)");
            statement.setString(1, bookClub);
            statement.setString(2, reader);

            statement.executeQuery();
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
