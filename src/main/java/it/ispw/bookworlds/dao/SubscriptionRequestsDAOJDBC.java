package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.SubscriptionRequestEntity;

import java.util.ArrayList;

public class SubscriptionRequestsDAOJDBC implements SubscriptionRequestDAO{
    @Override
    public ArrayList<SubscriptionRequestEntity> getRequestsByUsername(String username) {
        return null;
    }

    @Override
    public ArrayList<SubscriptionRequestEntity> getRequestsByBookClubName(String name) {
        return null;
    }

    @Override
    public boolean hasAlreadySentRequest(String bookClubName, String username) {
        return false;
    }

    @Override
    public void addSubscriptionRequest(SubscriptionRequestEntity request) {

    }

    @Override
    public void updateSubscriptionRequest(SubscriptionRequestEntity request) {

    }

    @Override
    public void deleteSubscriptionRequest(SubscriptionRequestEntity request) {

    }
}
