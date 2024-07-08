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
}
