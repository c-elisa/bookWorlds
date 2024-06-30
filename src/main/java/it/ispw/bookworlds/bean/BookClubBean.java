package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookClubBean {
    private String name;
    private List<Genre> genres;

    public BookClubBean(String name, List<Genre> genres){
        this.name = name;
        this.genres = genres;
    }

    public String getName(){return this.name;}

    public List<Genre> getGenres(){return this.genres;}
}
