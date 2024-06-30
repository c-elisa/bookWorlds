package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.model.Genre;

import java.util.ArrayList;

public class BookClubBean {
    private String name;
    private ArrayList<Genre> genres;

    public BookClubBean(String name, ArrayList<Genre> genres){
        this.name = name;
        this.genres = genres;
    }

    public String getName(){return this.name;}

    public ArrayList<Genre> getGenres(){return this.genres;}
}
