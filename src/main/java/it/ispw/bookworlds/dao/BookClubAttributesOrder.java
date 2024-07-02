package it.ispw.bookworlds.dao;

public enum BookClubAttributesOrder {
    NAME, OWNER, NUMBER_OF_SUBSCRIBERS, GENRES;

    public int getIndex(){return this.ordinal();}
}
