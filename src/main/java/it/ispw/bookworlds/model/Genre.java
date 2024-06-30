package it.ispw.bookworlds.model;

import it.ispw.bookworlds.utils.Printer;

public enum Genre {
    Fantasy,
    Scifi,
    Dystopian,
    Action,
    Thriller,
    Horror,
    Romance,
    YA,
    SelfHelp,
    Psychology,
    Poetry,
    Nonfiction,
    Manga,
    Classics,
    Art,
    Biography,
    Children,
    Crime,
    Music,
    Philosophy,
    Religion,
    Travel,
    History,
    Business;

    public static void printAsList(){
        for(Genre g: Genre.values()){
            Printer.println("[" + (Genre.valueOf(g.toString()).ordinal() + 1) + "] " + g);
        }
    }

    public static int possibleNumbers(){return Genre.values().length;}
}
