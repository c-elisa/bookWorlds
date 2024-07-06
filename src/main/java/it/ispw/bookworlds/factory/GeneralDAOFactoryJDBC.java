package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.*;

public class GeneralDAOFactoryJDBC extends GeneralDAOFactory {
    @Override
    public LoginDAO createLoginDao(){return new LoginDaoJDBC();}

    @Override
    public BookClubDAO createBookClubDAO() {
        return null;
    }

    @Override
    public SubscriptionRequestDAO createSubscriptionRequestDAO() {
        return null;
    }

    @Override
    public SubscribersDAO createSubscribersDAO() {
        return null;
    }
}
