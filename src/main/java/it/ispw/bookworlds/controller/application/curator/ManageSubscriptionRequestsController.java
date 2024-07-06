package it.ispw.bookworlds.controller.application.curator;

import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.dao.SubscriptionRequestDAO;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.model.SubscriptionRequestEntity;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ManageSubscriptionRequestsController {
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

    public void rejectRequest(SubscriptionRequestBean request){
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();

        request.setState(RequestState.REJECTED);
        SubscriptionRequestEntity requestEntity = new SubscriptionRequestEntity(request);
        subscriptionRequestDAO.updateSubscriptionRequest(requestEntity);
    }
}
