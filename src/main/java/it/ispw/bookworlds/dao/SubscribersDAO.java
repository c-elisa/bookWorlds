package it.ispw.bookworlds.dao;

public interface SubscribersDAO {
    boolean isSubscriber(String reader, String bookClub);
    public void addSubscriber(String bookClub, String reader);
}
