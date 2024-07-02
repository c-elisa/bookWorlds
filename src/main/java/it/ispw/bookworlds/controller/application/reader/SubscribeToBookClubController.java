package it.ispw.bookworlds.controller.application.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;

public class SubscribeToBookClubController {

    public GenresListBean getGenresList(){
        GenresListBean genresList = new GenresListBean();

        for(Genre g: Genre.getAsList()){
            genresList.getGenres().add(g.toString());
        }

        return genresList;
    }

    public ArrayList<BookClubBean> findBookClubs(GenresListBean selectedGenres){
        ArrayList<BookClubBean> bookClubBeans = new ArrayList<>();

        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        ArrayList<Genre> genres = new ArrayList<>();
        for(String genre: selectedGenres.getGenres()){
            genres.add(Genre.valueOf(genre));
        }

        ArrayList<BookClubEntity> bookClubsEntities = bookClubDAO.getBookClubsByGenres(genres);
        for(BookClubEntity bookClub: bookClubsEntities){
            bookClubBeans.add(new BookClubBean(
                                    bookClub.getName(),
                                    bookClub.getGenres(),
                                    bookClub.getNumberOfSubscribers(),
                                    bookClub.getOwner()));
        }

        return bookClubBeans;
    }
}
