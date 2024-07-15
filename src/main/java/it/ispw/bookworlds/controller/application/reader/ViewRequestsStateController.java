package it.ispw.bookworlds.controller.application.reader;

import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.dao.SubscriptionRequestDAO;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.SubscriptionRequestEntity;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ViewRequestsStateController {
    /**
     * Recupera le richieste di iscrizione inviate dall'utente corrente
     * @return una lista di Bean che contengono le richieste di iscrizione
     * @throws SessionNotFoundException se non esiste nessun account corrispondente al codice di sessione fornito
     */
    public List<SubscriptionRequestBean> retrieveSubscriptionRequests() throws SessionNotFoundException {
        List<SubscriptionRequestBean> requests = new ArrayList<>();
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();

        List<SubscriptionRequestEntity> requestsEntity = subscriptionRequestDAO.getRequestsByUsername(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());

        for(SubscriptionRequestEntity request: requestsEntity){
            requests.add(new SubscriptionRequestBean(request));
        }

        return requests;
    }

    /**
     * Chiama il metodo per eliminare le richieste dell'utente corrente che hanno già ricevuto risposta ed il cui stato è stato impostato ad ACCEPTED o REJECTED
     * @throws SessionNotFoundException se non esiste nessun account corrispondente al codice di sessione fornito
     */
    public void deleteRequests() throws SessionNotFoundException {
        SubscriptionRequestDAO subscriptionRequestDAO = GeneralDAOFactory.getInstance().createSubscriptionRequestDAO();

        subscriptionRequestDAO.deleteSubscriptionRequests(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername());
    }
}
