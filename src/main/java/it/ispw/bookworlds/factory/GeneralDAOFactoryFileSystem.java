package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.dao.LoginDAOFileSystem;
import it.ispw.bookworlds.dao.BookClubDAOFileSystem;

public class GeneralDAOFactoryFileSystem extends GeneralDAOFactory {
    @Override
    public LoginDAO createLoginDao(){return LoginDAOFileSystem.getInstance();}

    @Override
    public BookClubDAO createBookClubDAO() {return BookClubDAOFileSystem.getInstance();}
}
