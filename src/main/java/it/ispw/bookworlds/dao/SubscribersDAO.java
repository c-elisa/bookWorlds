package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.BookClubsNotFound;

import java.util.List;

public interface SubscribersDAO {
    boolean isSubscriber(String reader, String bookClub);
    void addSubscriber(String bookClub, String reader);
    void removeSubscriber(String bookClub, String reader);
    List<String> getReaderBookClubs(String reader) throws BookClubsNotFound;
    List<String> getSubscribers(String bookClub);
}
