package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;

public interface BookClubDAO {
    public void createBookClub(BookClubEntity bookClub);

    public ArrayList<BookClubEntity> getBookClubsByGenres(ArrayList<Genre> genres);

    public BookClubEntity getBookClubByName(String name);
}
