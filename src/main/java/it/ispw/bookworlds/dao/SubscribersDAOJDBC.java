package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribersDAOJDBC implements SubscribersDAO{
    private static SubscribersDAOJDBC instance = null;
    private static final String REMOVEQUERY = "DELETE FROM subscribers WHERE bookclub=? AND reader=?";
    private static final String ADDQUERY = "INSERT INTO subscribers VALUES (?,?)";

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
        modifySubscribers(bookClub, reader, ADDQUERY);
    }

    @Override
    public void removeSubscriber(String bookClub, String reader) {
        modifySubscribers(bookClub, reader, REMOVEQUERY);
    }

    @Override
    public List<String> getReaderBookClubs(String reader) throws BookClubsNotFound {
        Connection connection = ConnectionFactory.getInstance();
        List<String> bookClubs = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT bookclub FROM subscribers WHERE reader=?");
            statement.setString(1, reader);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                bookClubs.add(rs.getString(1));
            }

        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        if(bookClubs.isEmpty()) throw new BookClubsNotFound();
        return bookClubs;
    }

    private void modifySubscribers(String bookClub, String reader, String query){
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookClub);
            statement.setString(2, reader);

            statement.execute();
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
