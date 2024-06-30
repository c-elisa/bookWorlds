package it.ispw.bookworlds.model;

import it.ispw.bookworlds.utils.Printer;

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

    public static void printAsList(){
        for(Genre g: Genre.values()){
            Printer.println("[" + (Genre.valueOf(g.toString()).ordinal() + 1) + "] " + g);
        }
    }

    public static int possibleNumbers(){return Genre.values().length;}
}
