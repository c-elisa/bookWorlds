package it.ispw.bookworlds.factory;

import it.ispw.bookworlds.dao.*;
import it.ispw.bookworlds.exceptions.InvalidPersistenceTypeException;
import it.ispw.bookworlds.utils.ApplicationProperties;

/**
 * Interfaccia astratta per la Factory che ha la responsabilità di creare le varie classi DAO
 * Viene implementata come Singleton
 * A seconda del tipo di persistenza scelto, l'unica istanza verrà impostata ad una delle due Factory concrete
 */
public abstract class GeneralDAOFactory {
    private static GeneralDAOFactory instance = null;

    public static GeneralDAOFactory getInstance(){
        try {
            if (instance == null) {
                switch (ApplicationProperties.getProperty("persistence")) {
                    case "file" -> instance = new GeneralDAOFactoryFileSystem();
                    case "jdbc" -> instance = new GeneralDAOFactoryJDBC();
                    default -> throw new InvalidPersistenceTypeException();
                }
            }
        }catch(InvalidPersistenceTypeException e){
            //In caso di tipo di persistenza selezionata non valido, questa viene impostata per default a file system
            instance = new GeneralDAOFactoryFileSystem();
        }
        return instance;
    }

    public abstract AccountDAO createAccountDAO();

    public abstract LoginDAO createLoginDao();

    public abstract BookClubDAO createBookClubDAO();

    public abstract SubscriptionRequestDAO createSubscriptionRequestDAO();

    public abstract SubscribersDAO createSubscribersDAO();
}
