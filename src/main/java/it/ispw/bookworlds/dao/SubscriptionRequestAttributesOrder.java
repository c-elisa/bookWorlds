package it.ispw.bookworlds.dao;

public enum SubscriptionRequestAttributesOrder {
    BOOKCLUB_NAME, READER_USERNAME, STATE;

    public int getIndex(){return this.ordinal();}
}
