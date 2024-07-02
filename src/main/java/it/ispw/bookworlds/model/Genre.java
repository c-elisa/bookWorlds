package it.ispw.bookworlds.model;

import it.ispw.bookworlds.utils.Printer;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<Genre> getAsList(){
        return new ArrayList<Genre>(Arrays.asList(Genre.values()));
    }

    public static int possibleNumbers(){return Genre.values().length;}
}
