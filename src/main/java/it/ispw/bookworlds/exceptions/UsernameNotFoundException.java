package it.ispw.bookworlds.exceptions;

public class UsernameNotFoundException extends Exception {
    public static String MESSAGE = "Non esiste nessun account con questo username.";

    public UsernameNotFoundException(){super(MESSAGE);}
}
