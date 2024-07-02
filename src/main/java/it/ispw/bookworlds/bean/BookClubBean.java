package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.model.Genre;

import java.util.List;

public class BookClubBean {
    private String name;
    private List<Genre> genres;
    private int numberOfSubscribers;
    private String owner;

    public BookClubBean(String name, List<Genre> genres){
        this.name = name;
        this.genres = genres;
    }

    public BookClubBean(String name, List<Genre> genres, int subscribers, String owner){
        this.name = name;
        this.genres = genres;
        this.numberOfSubscribers = subscribers;
        this.owner = owner;
    }

    public String getName(){return this.name;}

    public List<Genre> getGenres(){return this.genres;}

    public int getNumberOfSubscribers(){return this.numberOfSubscribers;}

    public String getOwner(){return this.owner;}
}
