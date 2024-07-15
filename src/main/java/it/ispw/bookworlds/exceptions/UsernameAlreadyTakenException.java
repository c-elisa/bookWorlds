package it.ispw.bookworlds.exceptions;

public class UsernameAlreadyTakenException extends Exception {
    public static final String MESSAGE = "Esiste già un account con questo username";

    public UsernameAlreadyTakenException(){super(MESSAGE);}
}
