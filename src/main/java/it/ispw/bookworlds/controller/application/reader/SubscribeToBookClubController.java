package it.ispw.bookworlds.controller.application.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.GenericController;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscriptionRequestDAO;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.model.SubscriptionRequestEntity;

import java.util.ArrayList;

public class SubscribeToBookClubController extends GenericController {

    public ArrayList<BookClubBean> findBookClubs(GenresListBean selectedGenres){
        ArrayList<BookClubBean> bookClubBeans = new ArrayList<>();

        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        ArrayList<Genre> genres = new ArrayList<>();
        for(String genre: selectedGenres.getGenres()){
            genres.add(Genre.valueOf(genre));
        }

        ArrayList<BookClubEntity> bookClubsEntities = bookClubDAO.getBookClubsByGenres(genres);
        for(BookClubEntity bookClub: bookClubsEntities){
            bookClubBeans.add(new BookClubBean(
                                    bookClub.getName(),
                                    bookClub.getGenres(),
                                    bookClub.getNumberOfSubscribers(),
                                    bookClub.getCapacity(),
                                    bookClub.getOwner()));
        }

        return bookClubBeans;
    }

    public void makeSubscriptionRequest(SubscriptionRequestBean request){
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();

        BookClubEntity bookClub = bookClubDAO.getBookClubByName(request.getBookClubName());

        if(bookClub.isFull()){
            request.setState(RequestState.REJECTED);
        }
        else {
            request.setState(RequestState.PENDING);
            SubscriptionRequestEntity newRequest = new SubscriptionRequestEntity(request);
            subscriptionRequestDAO.addSubscriptionRequest(newRequest);
        }
    }
}
