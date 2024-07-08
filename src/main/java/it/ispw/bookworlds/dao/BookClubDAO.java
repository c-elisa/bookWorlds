package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.List;

public interface BookClubDAO {
    public void createBookClub(BookClubEntity bookClub);

    public List<BookClubEntity> getBookClubsByGenres(List<Genre> genres);

    public List<BookClubEntity> getBookClubsByOwner(String name);

    public BookClubEntity getBookClubByName(String name);

    public void addSubscriber(String name);

}
