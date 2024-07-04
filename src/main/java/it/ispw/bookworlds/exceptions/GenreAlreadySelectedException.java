package it.ispw.bookworlds.exceptions;

public class GenreAlreadySelectedException extends Exception {
    public static final String MESSAGE = "Questo genere è già stato selezionato";

    public GenreAlreadySelectedException(){super(MESSAGE);}
    public GenreAlreadySelectedException(String message){super(message);}
}
