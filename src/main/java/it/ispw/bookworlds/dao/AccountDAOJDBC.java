package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOJDBC implements AccountDAO {
    private static AccountDAOJDBC instance = null;

    private AccountDAOJDBC(){}

    public static AccountDAOJDBC getInstance(){
        if(instance == null) instance = new AccountDAOJDBC();
        return instance;
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        //account da restituire
        AccountEntity account = null;

        Connection connection = ConnectionFactory.getInstance();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT code,username,email,role FROM account WHERE username=?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                account = new AccountEntity(rs.getInt("code"),
                        rs.getString("username"), rs.getString("email"), Role.getRole(rs.getString("role")));
            }

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return account;
    }

    @Override
    public void signUp(int code, String username, String password, String email, Role role) {
        Connection connection = ConnectionFactory.getInstance();

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO account VALUES (?,?,?,?,?)");
            statement.setInt(1, code);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, String.valueOf(role));

            statement.execute();

        }catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public int getMaxCode() {
        Connection connection = ConnectionFactory.getInstance();
        int code = 0;

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(code) FROM account");

            ResultSet rs = statement.executeQuery();

            if(rs.next()) code = rs.getInt(1);
        } catch (SQLException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return code;
    }
}
