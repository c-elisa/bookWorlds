package it.ispw.bookworlds.model;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class BookClubEntity {
    private String name;
    private List<Genre> genres;
    private int numberOfSubscribers;
    private String owner;
    private ArrayList<AccountEntity> subscribersList;
    private ArrayList<BookEntity> readingList;

    public BookClubEntity(BookClubBean bean) throws SessionNotFoundException {
        this.name = bean.getName();
        this.genres = bean.getGenres();
        this.numberOfSubscribers = 0;
        this.owner = SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername();
        this.subscribersList = new ArrayList<AccountEntity>();
        this.readingList = new ArrayList<BookEntity>();
    }

    public BookClubEntity(String name, List<Genre> genres, int subscribers, String owner){
        this.name = name;
        this.genres = genres;
        this.numberOfSubscribers = subscribers;
        this.owner = owner;
        this.subscribersList = new ArrayList<AccountEntity>();
        this.readingList = new ArrayList<BookEntity>();
    }

    public String getName(){return this.name;}

    public String getOwner(){return owner;}

    public int getNumberOfSubscribers(){return this.numberOfSubscribers;}

    public List<Genre> getGenres(){return this.genres;}
}
