package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.model.Genre;

public class GenericController {
    /**
     * Recupera la lista di tutti i generi presenti nella enum Genre
     * @return GenresListBean contenente una lista di stringhe con tutti i generi
     */
    public GenresListBean getGenresList(){
        GenresListBean genresList = new GenresListBean();

        for(Genre g: Genre.getAsList()){
            genresList.addGenre(g.toString());
        }

        return genresList;
    }
}
