package it.ispw.bookworlds.model;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.bookclub.state.BookClubStateMachine;
import it.ispw.bookworlds.model.bookclub.state.BookClubStateMachineImpl;
import it.ispw.bookworlds.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class BookClubEntity {
    private String name;
    private List<Genre> genres;
    private int numberOfSubscribers;
    private int capacity;
    private String owner;
    private ArrayList<AccountEntity> subscribersList;
    private ArrayList<BookEntity> readingList;

    private BookClubStateMachine bookClubSM = new BookClubStateMachineImpl();

    public BookClubEntity(BookClubBean bean) throws SessionNotFoundException {
        this.name = bean.getName();
        this.genres = bean.getGenres();
        this.numberOfSubscribers = 0;
        this.capacity = bean.getCapacity();
        this.owner = SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getUsername();
        this.subscribersList = new ArrayList<AccountEntity>();
        this.readingList = new ArrayList<BookEntity>();

        if(numberOfSubscribers >= capacity) bookClubSM.markAsFull();
    }

    public BookClubEntity(String name, List<Genre> genres, int subscribers, int capacity, String owner){
        this.name = name;
        this.genres = genres;
        this.numberOfSubscribers = subscribers;
        this.capacity = capacity;
        this.owner = owner;
        this.subscribersList = new ArrayList<AccountEntity>();
        this.readingList = new ArrayList<BookEntity>();

        if(numberOfSubscribers >= capacity) bookClubSM.markAsFull();
    }

    public String getName(){return this.name;}

    public String getOwner(){return owner;}

    public int getNumberOfSubscribers(){return this.numberOfSubscribers;}

    public int getCapacity(){return this.capacity;}

    public List<Genre> getGenres(){return this.genres;}

    public boolean isFull(){return bookClubSM.isFull();}

    public boolean isNotFull(){return bookClubSM.isNotFull();}
}
