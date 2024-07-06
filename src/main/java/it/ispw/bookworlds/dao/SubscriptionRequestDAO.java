package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.SubscriptionRequestEntity;

import java.util.ArrayList;

public interface SubscriptionRequestDAO {
    public ArrayList<SubscriptionRequestEntity> getRequestsByUsername(String username);

    public ArrayList<SubscriptionRequestEntity> getRequestsByBookClubName(String name);

    boolean hasAlreadySentRequest(String bookClubName, String username);

    public void addSubscriptionRequest(SubscriptionRequestEntity request);

    public void updateSubscriptionRequest(SubscriptionRequestEntity request);

    public void deleteSubscriptionRequest(SubscriptionRequestEntity request);
}
