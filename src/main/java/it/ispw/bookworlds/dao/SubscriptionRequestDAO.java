package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.SubscriptionRequestEntity;

import java.util.List;

public interface SubscriptionRequestDAO {
    public List<SubscriptionRequestEntity> getRequestsByUsername(String username);

    public List<SubscriptionRequestEntity> getRequestsByBookClubName(String name);

    boolean hasAlreadySentRequest(String bookClubName, String username);

    public void addSubscriptionRequest(SubscriptionRequestEntity request);

    public void updateSubscriptionRequest(SubscriptionRequestEntity request);

    public void deleteSubscriptionRequest(SubscriptionRequestEntity request);
}
