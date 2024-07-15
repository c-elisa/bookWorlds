package it.ispw.bookworlds.controller.application.curator;

import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveSubscriberController {
    /**
     * Recupera i nomi dei club del libro dei quali l'utente corrente è proprietario
     * @return una lista contenente i nomi dei club del libro
     * @throws SessionNotFoundException se non esiste nessun account corrispondente al codice di sessione fornito
     */
    public List<String> retrieveOwnerBookClubs() throws SessionNotFoundException {
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        List<BookClubEntity> bookClubsEntity = bookClubDAO.getBookClubsByOwner(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());
        List<String> bookClubsNames = new ArrayList<>();

        for(BookClubEntity bookClub: bookClubsEntity) bookClubsNames.add(bookClub.getName());

        return bookClubsNames;
    }

    /**
     * Recupera i nomi degli utenti iscritti al club del libro
     * @param bookClub è il nome del club del libro di cui si vogliono trovare gli iscritti
     * @return una lista contenente i nomi di tutti gli iscritti al club del libro specificato
     */
    public List<String> retrieveReadersByBookClubName(String bookClub){
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        return subscribersDAO.getSubscribers(bookClub);
    }

    /**
     * Rimuove un iscritto da un club del libro
     * @param bookClub è il club del libro da cui rimuovere l'iscritto
     * @param reader è l'iscritto da rimuovere
     */
    public void removeSubscriber(String bookClub, String reader){
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        subscribersDAO.removeSubscriber(bookClub, reader);
        bookClubDAO.removeSubscriber(bookClub);
    }
}
