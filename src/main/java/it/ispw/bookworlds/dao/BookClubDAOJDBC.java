package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;

public class BookClubDAOJDBC implements BookClubDAO{
    @Override
    public void createBookClub(BookClubEntity bookClub) {

    }

    @Override
    public ArrayList<BookClubEntity> getBookClubsByGenres(ArrayList<Genre> genres) {
        return null;
    }

    @Override
    public ArrayList<BookClubEntity> getBookClubsByOwner(String name) {
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
