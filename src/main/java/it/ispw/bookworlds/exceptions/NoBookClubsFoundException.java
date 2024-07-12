package it.ispw.bookworlds.exceptions;

public class NoBookClubsFoundException extends Exception {
    public static final String MESSAGE = "Nessun club del libro corrispondente ai generi selezionati.";

    public NoBookClubsFoundException(){super(MESSAGE);}
    public NoBookClubsFoundException(String message){super(message);}
}
