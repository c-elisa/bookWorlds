package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;

import java.util.List;

public class BookClubBean {
    private String name;
    private List<Genre> genres;
    private int numberOfSubscribers;
    private int capacity;
    private String owner;

    public BookClubBean(String name, List<Genre> genres, int capacity){
        this.name = name;
        this.genres = genres;
        this.capacity = capacity;
    }

    public BookClubBean(String name, List<Genre> genres, int subscribers, int capacity, String owner){
        this.name = name;
        this.genres = genres;
        this.numberOfSubscribers = subscribers;
        this.capacity = capacity;
        this.owner = owner;
    }

    public String getName(){return this.name;}

    public List<Genre> getGenres(){return this.genres;}

    public int getNumberOfSubscribers(){return this.numberOfSubscribers;}

    public int getCapacity(){return this.capacity;}

    public String getOwner(){return this.owner;}
}
