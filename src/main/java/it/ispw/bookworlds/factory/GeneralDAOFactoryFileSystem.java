package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.dao.LoginDAOFileSystem;

public class GeneralDAOFactoryFileSystem extends GeneralDAOFactory {
    @Override
    public LoginDAO createLoginDao(){return LoginDAOFileSystem.getInstance();}
}
