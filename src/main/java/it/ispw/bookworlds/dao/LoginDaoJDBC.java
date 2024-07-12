package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.factory.ConnectionFactory;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginDaoJDBC implements LoginDAO{
    private static LoginDaoJDBC instance = null;

    private LoginDaoJDBC(){}

    public static LoginDaoJDBC getInstance(){
        if(instance == null) instance = new LoginDaoJDBC();
        return instance;
    }

    @Override
    public AccountEntity login(String username, String password) throws IncorrectPasswordException, UsernameNotFoundException {
        AccountEntity account = null;

        Connection connection = ConnectionFactory.getInstance();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT code,username,password,email,role FROM account WHERE username=?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                if(!Objects.equals(rs.getString("password"), password)) throw new IncorrectPasswordException();
                account = new AccountEntity(rs.getInt("code"),
                        rs.getString("username"), rs.getString("email"), Role.getRole(rs.getString("role")));
            }
            else throw new UsernameNotFoundException();

        } catch (SQLException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return account;
    }
}
