package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.model.Genre;

public class GenericController {
    public GenresListBean getGenresList(){
        GenresListBean genresList = new GenresListBean();

        for(Genre g: Genre.getAsList()){
            genresList.addGenre(g.toString());
        }

        return genresList;
    }
}
