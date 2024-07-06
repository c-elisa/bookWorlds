package it.ispw.bookworlds.exceptions;

public class NoRequestSelectedException extends Exception {
    public static final String MESSAGE = "Bisogna selezionare una richiesta";

    public NoRequestSelectedException(){super(MESSAGE);}
    public NoRequestSelectedException(String message){super(message);}
}
