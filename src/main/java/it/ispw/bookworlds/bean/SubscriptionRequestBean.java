package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.bean.observer.Observer;
import it.ispw.bookworlds.bean.observer.Subject;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.utils.SessionManager;

public class SubscriptionRequestBean extends Subject {
    private String readerUsername;
    private String bookClubName;
    private RequestState state;

    public SubscriptionRequestBean(String name) throws SessionNotFoundException {
        this.readerUsername = SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername();
        this.bookClubName = name;
        this.state = RequestState.NO_STATE;
    }

    public SubscriptionRequestBean(String name, RequestState state) throws SessionNotFoundException {
        this.readerUsername = SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername();
        this.bookClubName = name;
        this.state = state;
    }

    public String getReaderUsername(){return readerUsername;}

    public String getBookClubName(){return bookClubName;}

    public RequestState getState(){return state;}

    public void setState(RequestState state){
        this.state = state;
        notifyObservers();
    }

    public void notifyObservers(){
        for(Observer obs: observers){
            obs.update(this.state);
        }
    }

}
