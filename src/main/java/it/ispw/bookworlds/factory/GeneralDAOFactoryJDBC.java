package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.*;

/**
 * Factory concreta che si occupa della creazione delle classi DAO della famiglia JDBC
 */
public class GeneralDAOFactoryJDBC extends GeneralDAOFactory {
    @Override
    public AccountDAO createAccountDAO() {return AccountDAOJDBC.getInstance();}

    @Override
    public LoginDAO createLoginDao(){return LoginDaoJDBC.getInstance();}

    @Override
    public BookClubDAO createBookClubDAO() {return BookClubDAOJDBC.getInstance();}

    @Override
    public SubscriptionRequestDAO createSubscriptionRequestDAO() {return SubscriptionRequestDAOJDBC.getInstance();}

    @Override
    public SubscribersDAO createSubscribersDAO() {return SubscribersDAOJDBC.getInstance();}
}
