package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.List;

public interface BookClubDAO {
    void createBookClub(BookClubEntity bookClub);

    List<BookClubEntity> getBookClubsByGenres(List<Genre> genres) throws NoBookClubsFoundException;

    List<BookClubEntity> getBookClubsByOwner(String name);

    BookClubEntity getBookClubByName(String name);

    void addSubscriber(String name);

    void removeSubscriber(String name);

}
