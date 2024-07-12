package it.ispw.bookworlds.exceptions;

public class BookClubsNotFound extends Exception {
    private static final String MESSAGE = "Non sei iscritto a nessun club del libro.";

    public BookClubsNotFound(){super(MESSAGE);}
    public BookClubsNotFound(String message){super(message);}
}
