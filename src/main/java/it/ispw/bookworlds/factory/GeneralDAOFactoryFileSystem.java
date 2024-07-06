package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.*;

public class GeneralDAOFactoryFileSystem extends GeneralDAOFactory {
    @Override
    public LoginDAO createLoginDao(){return LoginDAOFileSystem.getInstance();}

    @Override
    public BookClubDAO createBookClubDAO() {return BookClubDAOFileSystem.getInstance();}

    @Override
    public SubscriptionRequestDAO createSubscriptionRequestDAO() {return SubscriptionRequestDAOFileSystem.getInstance();}

    @Override
    public SubscribersDAO createSubscribersDAO() {return SubscribersDAOFileSystem.getInstance();}
}
