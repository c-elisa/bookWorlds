package it.ispw.bookworlds.model;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;

public class SubscriptionRequestEntity {
    private String readerUsername;
    private RequestState state;
    private String bookClubName;

    public SubscriptionRequestEntity(String username, String bookClubName, RequestState state){
        this.readerUsername = username;
        this.bookClubName = bookClubName;
        this.state = state;
    }

    public SubscriptionRequestEntity(SubscriptionRequestBean bean){
        this.readerUsername = bean.getReaderUsername();
        this.bookClubName = bean.getBookClubName();
        this.state = bean.getState();
    }

    public String getReaderUsername(){return this.readerUsername;}
    public String getBookClubName(){return this.bookClubName;}
    public RequestState getState(){return this.state;}
    public void setState(RequestState newState){this.state = newState;}
}
