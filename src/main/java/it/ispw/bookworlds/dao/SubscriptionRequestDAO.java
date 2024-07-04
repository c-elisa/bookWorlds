package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.SubscriptionRequestEntity;

import java.util.ArrayList;

public interface SubscriptionRequestDAO {
    public ArrayList<SubscriptionRequestEntity> getRequestsByUsername(String username);

    public ArrayList<SubscriptionRequestEntity> getRequestsByBookClubName(String name);

    public void addSubscriptionRequest(SubscriptionRequestEntity request);
}
