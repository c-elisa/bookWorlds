package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookClubDAOJDBC implements BookClubDAO{
    @Override
    public void createBookClub(BookClubEntity bookClub) {

    }

    @Override
    public List<BookClubEntity> getBookClubsByGenres(List<Genre> genres) {
        return null;
    }

    @Override
    public List<BookClubEntity> getBookClubsByOwner(String name) {
        return null;
    }

    @Override
    public BookClubEntity getBookClubByName(String name) {
        return null;
    }

    @Override
    public void addSubscriber(String name) {

    }
}
