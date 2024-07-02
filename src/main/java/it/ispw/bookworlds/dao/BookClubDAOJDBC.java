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
}
