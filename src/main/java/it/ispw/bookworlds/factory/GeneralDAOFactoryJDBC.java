package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.dao.LoginDaoJDBC;
import it.ispw.bookworlds.dao.SubscriptionRequestDAO;

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
}
