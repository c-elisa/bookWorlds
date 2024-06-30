package it.ispw.bookworlds.exceptions;

public class NoGenresSelectedException extends Exception {
    public static final String MESSAGE = "Bisogna selezionare almeno un genere";

    public NoGenresSelectedException(){super(MESSAGE);}
    public NoGenresSelectedException(String message){super(message);}
}
