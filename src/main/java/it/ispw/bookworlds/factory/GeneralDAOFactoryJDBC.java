package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.dao.LoginDAOFileSystem;
import it.ispw.bookworlds.dao.LoginDaoJDBC;

public class GeneralDAOFactoryJDBC extends GeneralDAOFactory {
    @Override
    public LoginDAO createLoginDao(){return new LoginDaoJDBC();}

    @Override
    public BookClubDAO createBookClubDAO() {
        return null;
    }
}
