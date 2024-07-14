package it.ispw.bookworlds.controller.application.curator;

import it.ispw.bookworlds.bean.BookClubBean;
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
    public List<String> retrieveOwnerBookClubs() throws SessionNotFoundException {
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        List<BookClubEntity> bookClubsEntity = bookClubDAO.getBookClubsByOwner(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());
        List<String> bookClubsNames = new ArrayList<>();

        for(BookClubEntity bookClub: bookClubsEntity) bookClubsNames.add(bookClub.getName());

        return bookClubsNames;
    }

    public List<String> retrieveReadersByBookClubName(String bookClub){
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        return subscribersDAO.getSubscribers(bookClub);
    }

    public void removeSubscriber(String bookClub, String reader){
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        subscribersDAO.removeSubscriber(bookClub, reader);
        bookClubDAO.removeSubscriber(bookClub);
    }
}
