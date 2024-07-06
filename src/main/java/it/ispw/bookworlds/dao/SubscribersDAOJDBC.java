package it.ispw.bookworlds.dao;

public class SubscribersDAOJDBC implements SubscribersDAO{
    @Override
    public boolean isSubscriber(String reader, String bookClub) {
        return false;
    }

    @Override
    public void addSubscriber(String bookClub, String reader) {

    }
}
