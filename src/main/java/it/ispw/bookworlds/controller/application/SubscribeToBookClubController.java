package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.dao.SubscriptionRequestDAO;
import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.model.SubscriptionRequestEntity;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SubscribeToBookClubController extends GenericController {

    /**
     * Permette di recuperare la lista dei club del libro relativi ai generi specificati
     * @param selectedGenres Bean contenente la lista con i generi che sono stati selezionati dall'utente
     * @return una lista di BookClubBean che contengono le informazioni dei club del libro
     * @throws NoBookClubsFoundException quando non esistono club del libro relativi ai generi selezionati
     */

    public List<BookClubBean> findBookClubs(GenresListBean selectedGenres) throws NoBookClubsFoundException {
        ArrayList<BookClubBean> bookClubBeans = new ArrayList<>();

        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        ArrayList<Genre> genres = new ArrayList<>();
        for(String genre: selectedGenres.getGenres()){
            genres.add(Genre.valueOf(genre));
        }

        List<BookClubEntity> bookClubsEntities = bookClubDAO.getBookClubsByGenres(genres);
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

    /**
     * Metodo per effettuare l'inoltro di una richiesta di iscrizione.
     * Se il club del libro è pieno, la richiesta sarà impostata a REJECTED e non sarà inoltrata.
     * Se il lettore è già iscritto al club del libro oppure ha già inviato una richiesta per esso, la richiesta sarà impostata a DUPLICATE e non sarà inoltrata.
     * Altrimenti, la richiesta viene impostata a PENDING e viene inoltrata correttamente.
     * @param request Bean che contiene i dati relativi alla richiesta da inoltrare
     */

    public void makeSubscriptionRequest(SubscriptionRequestBean request){
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        BookClubEntity bookClub = bookClubDAO.getBookClubByName(request.getBookClubName());

        if(bookClub.isFull()) request.setState(RequestState.REJECTED);
        else if(subscribersDAO.isSubscriber(request.getReaderUsername(), request.getBookClubName())
                || subscriptionRequestDAO.hasAlreadySentRequest(request.getBookClubName(), request.getReaderUsername())) request.setState(RequestState.DUPLICATE);
        else {
            request.setState(RequestState.PENDING);
            SubscriptionRequestEntity newRequest = new SubscriptionRequestEntity(request);
            subscriptionRequestDAO.addSubscriptionRequest(newRequest);
        }
    }

    /**
     * Recupera le richieste di iscrizione relative ai club del libro di cui l'utente che ha chiamato il metodo è proprietario.
     * @return una lista di Bean contenenti le richieste di iscrizione
     * @throws SessionNotFoundException se non esiste alcun account corrispondente al codice di sessione fornito
     */

    public List<SubscriptionRequestBean> retrieveSubscriptionRequests() throws SessionNotFoundException {
        String username = SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername();

        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        List<BookClubEntity> bookClubs = bookClubDAO.getBookClubsByOwner(username);

        ArrayList<SubscriptionRequestEntity> requests = new ArrayList<>();
        for(BookClubEntity bookClub: bookClubs) requests.addAll(subscriptionRequestDAO.getRequestsByBookClubName(bookClub.getName()));

        ArrayList<SubscriptionRequestBean> requestsBean = new ArrayList<>();
        for(SubscriptionRequestEntity request: requests) requestsBean.add(new SubscriptionRequestBean(request));

        return requestsBean;
    }

    /**
     * Metodo per accettare una richiesta di iscrizione ad un club del libro.
     * Una volta accettata, il lettore viene aggiunto alla lista degli iscritti per quel club del libro.
     * @param request Bean che contiene i dati della richiesta da accettare
     */

    public void acceptRequest(SubscriptionRequestBean request){
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();
        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        request.setState(RequestState.ACCEPTED);
        SubscriptionRequestEntity requestEntity = new SubscriptionRequestEntity(request);
        subscriptionRequestDAO.updateSubscriptionRequest(requestEntity);
        bookClubDAO.addSubscriber(requestEntity.getBookClubName());
        subscribersDAO.addSubscriber(requestEntity.getBookClubName(), requestEntity.getReaderUsername());
    }

    /**
     * Metodo per rifiutare una richiesta di iscrizione ad un club del libro.
     * @param request Bean che contiene i dati della richiesta da rifiutare
     */

    public void rejectRequest(SubscriptionRequestBean request){
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();

        request.setState(RequestState.REJECTED);
        SubscriptionRequestEntity requestEntity = new SubscriptionRequestEntity(request);
        subscriptionRequestDAO.updateSubscriptionRequest(requestEntity);
    }
}
