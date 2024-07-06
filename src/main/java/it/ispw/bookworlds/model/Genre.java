package it.ispw.bookworlds.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Genre {
    FANTASY,
    SCI_FI,
    DYSTOPIAN,
    ACTION,
    THRILLER,
    HORROR,
    ROMANCE,
    YA,
    SELF_HELP,
    PSYCHOLOGY,
    POETRY,
    NONFICTION,
    MANGA,
    CLASSICS,
    ART,
    BIOGRAPHY,
    CHILDREN,
    CRIME,
    MUSIC,
    PHILOSOPHY,
    RELIGION,
    TRAVEL,
    HISTORY,
    BUSINESS;

    public static List<Genre> getAsList(){
        return new ArrayList<Genre>(Arrays.asList(Genre.values()));
    }

    public static int possibleNumbers(){return Genre.values().length;}
}
