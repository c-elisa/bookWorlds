package it.ispw.bookworlds.controller.application.reader;

import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.List;

public class UnsubscribeFromBookClubController {
    public List<String> retrieveBookClubs() throws SessionNotFoundException, BookClubsNotFound {
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        return subscribersDAO.getReaderBookClubs(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());
    }

    public void unsubscribe(String bookClub) throws SessionNotFoundException {
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        subscribersDAO.removeSubscriber(bookClub, SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());
        bookClubDAO.removeSubscriber(bookClub);
    }
}
