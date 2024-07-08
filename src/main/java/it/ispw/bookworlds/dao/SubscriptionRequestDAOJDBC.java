package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.model.SubscriptionRequestEntity;
import it.ispw.bookworlds.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRequestDAOJDBC implements SubscriptionRequestDAO{
    private static SubscriptionRequestDAOJDBC instance = null;

    private SubscriptionRequestDAOJDBC(){}

    public static SubscriptionRequestDAOJDBC getInstance(){
        if(instance == null) instance = new SubscriptionRequestDAOJDBC();
        return instance;
    }
    @Override
    public List<SubscriptionRequestEntity> getRequestsByUsername(String username) {
        List<SubscriptionRequestEntity> requests = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT bookclub, reader, state FROM subscription_requests WHERE reader=?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                requests.add(new SubscriptionRequestEntity(rs.getString("reader"), rs.getString("bookclub"),
                        RequestState.valueOf(rs.getString("state"))));
            }
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return requests;
    }

    @Override
    public List<SubscriptionRequestEntity> getRequestsByBookClubName(String name) {
        List<SubscriptionRequestEntity> requests = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT bookclub, reader, state FROM subscription_requests WHERE bookclub=?");
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                requests.add(new SubscriptionRequestEntity(rs.getString("reader"), rs.getString("bookclub"),
                        RequestState.valueOf(rs.getString("state"))));
            }
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return requests;
    }

    @Override
    public boolean hasAlreadySentRequest(String bookClubName, String username) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT bookclub, reader, state FROM subscription_requests WHERE bookclub=? AND reader=? AND state=?");
            statement.setString(1, bookClubName);
            statement.setString(2, username);
            statement.setString(3, String.valueOf(RequestState.PENDING));

            ResultSet rs = statement.executeQuery();

            if(rs.next()) return true;

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return false;
    }

    @Override
    public void addSubscriptionRequest(SubscriptionRequestEntity request) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO subscription_requests VALUES (?,?,?)");
            statement.setString(1, request.getBookClubName());
            statement.setString(2, request.getReaderUsername());
            statement.setString(3, String.valueOf(request.getState()));

            statement.executeQuery();
        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public void updateSubscriptionRequest(SubscriptionRequestEntity request) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE subscription_requests SET state=? WHERE bookclub=? AND reader=?");
            statement.setString(1, String.valueOf(request.getState()));
            statement.setString(2, request.getBookClubName());
            statement.setString(3, request.getReaderUsername());

            statement.executeQuery();
        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public void deleteSubscriptionRequest(SubscriptionRequestEntity request) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM subscription_requests WHERE bookclub=? AND reader=?");
            statement.setString(1, request.getBookClubName());
            statement.setString(2, request.getReaderUsername());

            statement.executeQuery();
        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
