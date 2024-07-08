package it.ispw.bookworlds.dao;

public enum BookClubAttributesOrder {
    NAME, OWNER, NUMBER_OF_SUBSCRIBERS, CAPACITY, GENRES;

    public int getIndex(){return this.ordinal();}

    public int getincrementedIndex(){return this.ordinal() + 1;}
}
