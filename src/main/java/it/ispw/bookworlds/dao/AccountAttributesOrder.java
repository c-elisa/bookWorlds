package it.ispw.bookworlds.dao;

public enum AccountAttributesOrder {
    CODE, USERNAME, PASSWORD, EMAIL, ROLE;

    public int getIndex(){return this.ordinal();}
}
