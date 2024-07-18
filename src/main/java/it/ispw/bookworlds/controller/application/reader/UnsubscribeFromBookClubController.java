package it.ispw.bookworlds.controller.application.reader;

import it.ispw.bookworlds.utils.CurrentSession;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.List;

public class UnsubscribeFromBookClubController {
    /**
     * Recupera i nomi dei club del libro ai quali l'utente corrente è iscritto
     * @return una lista di stringhe contenente i nomi del club del libro
     * @throws SessionNotFoundException se non esiste nessun account corrispondente al codice di sessione fornito
     * @throws BookClubsNotFound se l'utente non è iscritto a nessun club del libro
     */
    public List<String> retrieveBookClubs() throws SessionNotFoundException, BookClubsNotFound {
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        return subscribersDAO.getReaderBookClubs(SessionManager.getAccountBySessionId(CurrentSession.getSessionId()).getUsername());
    }

    /**
     * Disiscrive l'utente corrente da un club del libro
     * @param bookClub contiene il nome del club del libro dal quale ci si vuole disiscrivere
     * @throws SessionNotFoundException se non esiste nessun account corrispondente al codice di sessione fornito
     */
    public void unsubscribe(String bookClub) throws SessionNotFoundException {
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        subscribersDAO.removeSubscriber(bookClub, SessionManager.getAccountBySessionId(CurrentSession.getSessionId()).getUsername());
        bookClubDAO.removeSubscriber(bookClub);
    }
}
